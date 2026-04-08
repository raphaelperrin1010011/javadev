package fr.kingsfall.langflags;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;

public class LangFlags extends JavaPlugin implements Listener {

    private PlayerData playerData;
    private GeoLocator geoLocator;
    private FlagParticleTask particleTask;

    @Override
    public void onEnable() {
        playerData = new PlayerData();
        geoLocator = new GeoLocator(this, playerData);

        // Register /lang command
        LangCommand langCommand = new LangCommand(playerData);
        getCommand("olympe").setExecutor(langCommand);
        getCommand("olympe").setTabCompleter(langCommand);

        // Register events
        getServer().getPluginManager().registerEvents(this, this);

        // Start particle task - runs every 5 ticks (0.25s)
        particleTask = new FlagParticleTask(playerData);
        particleTask.runTaskTimer(this, 20L, 5L);

        getLogger().info("LangFlags enabled - Geolocation + Flag Particles");
    }

    @Override
    public void onDisable() {
        if (particleTask != null) {
            particleTask.cancel();
        }
        getLogger().info("LangFlags disabled");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Detect country async, then show welcome title
        geoLocator.detectCountry(event.getPlayer(), countryCode -> {
            PlayerData.PlayerInfo info = playerData.get(event.getPlayer().getUniqueId());
            String lang = info.getLangCode();

            // Show welcome title after detection
            Component title = Component.text(Messages.WELCOME.get(lang))
                    .color(NamedTextColor.GOLD)
                    .decoration(TextDecoration.BOLD, true);

            Component subtitle = Component.text(Messages.WELCOME_SUBTITLE.get(lang))
                    .color(NamedTextColor.YELLOW);

            Title.Times times = Title.Times.times(
                    Duration.ofMillis(500),
                    Duration.ofMillis(4000),
                    Duration.ofMillis(1000)
            );

            event.getPlayer().showTitle(Title.title(title, subtitle, times));

            // Chat message with detected country
            event.getPlayer().sendMessage(
                    Component.text("\u2691 ", NamedTextColor.WHITE)
                            .append(Component.text(Messages.LANG_CURRENT.get(lang), NamedTextColor.AQUA))
                            .append(Component.text(" [" + countryCode + "]", NamedTextColor.GRAY))
            );
        });
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        playerData.remove(event.getPlayer().getUniqueId());
    }
}
