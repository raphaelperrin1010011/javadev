package fr.kingsfall.langflags;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.function.Consumer;
import java.util.logging.Level;

public class GeoLocator {

    private final Plugin plugin;
    private final PlayerData playerData;

    public GeoLocator(Plugin plugin, PlayerData playerData) {
        this.plugin = plugin;
        this.playerData = playerData;
    }

    /**
     * Detects the player's country asynchronously via ip-api.com,
     * then runs the callback on the main thread with the country code.
     */
    public void detectCountry(Player player, Consumer<String> onComplete) {
        InetSocketAddress address = player.getAddress();
        if (address == null) {
            onComplete.accept("FR");
            return;
        }

        String ip = address.getAddress().getHostAddress();

        // Localhost = dev environment, default to FR
        if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1") || ip.startsWith("192.168.")) {
            plugin.getLogger().info("[GeoLocator] Local IP detected for " + player.getName() + ", defaulting to FR");
            PlayerData.PlayerInfo info = playerData.get(player.getUniqueId());
            info.setCountryCode("FR");
            info.setLangCode("fr");
            onComplete.accept("FR");
            return;
        }

        // Async HTTP call - don't block the main thread
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            String countryCode = fetchCountryCode(ip);
            // Back to main thread
            Bukkit.getScheduler().runTask(plugin, () -> {
                PlayerData.PlayerInfo info = playerData.get(player.getUniqueId());
                info.setCountryCode(countryCode);
                info.setLangCode(FlagDesign.getLangForCountry(countryCode));
                plugin.getLogger().info("[GeoLocator] " + player.getName() + " -> " + countryCode
                        + " (lang: " + info.getLangCode() + ")");
                onComplete.accept(countryCode);
            });
        });
    }

    private String fetchCountryCode(String ip) {
        try {
            URI uri = URI.create("http://ip-api.com/json/" + ip + "?fields=status,countryCode");
            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            if (conn.getResponseCode() == 200) {
                try (InputStreamReader reader = new InputStreamReader(conn.getInputStream())) {
                    JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                    if ("success".equals(json.get("status").getAsString())) {
                        return json.get("countryCode").getAsString();
                    }
                }
            }
            conn.disconnect();
        } catch (Exception e) {
            plugin.getLogger().log(Level.WARNING, "[GeoLocator] Failed to fetch country for IP: " + ip, e);
        }
        return "FR"; // fallback
    }
}
