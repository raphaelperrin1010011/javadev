package fr.kingsfall.langflags;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class LangCommand implements CommandExecutor, TabCompleter {

    private static final Set<String> VALID_LANGS = Set.of("fr", "en", "es", "de", "pt", "it", "ja", "ru");

    private final PlayerData playerData;

    public LangCommand(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        PlayerData.PlayerInfo info = playerData.get(player.getUniqueId());

        // /lang off - disable particles
        if (args.length == 1 && args[0].equalsIgnoreCase("off")) {
            info.setParticlesEnabled(false);
            player.sendMessage(Component.text(
                    Messages.PARTICLES_OFF.get(info.getLangCode()),
                    NamedTextColor.GRAY
            ));
            return true;
        }

        // /lang on - enable particles
        if (args.length == 1 && args[0].equalsIgnoreCase("on")) {
            info.setParticlesEnabled(true);
            player.sendMessage(Component.text(
                    Messages.PARTICLES_ON.get(info.getLangCode()),
                    NamedTextColor.GREEN
            ));
            return true;
        }

        // /lang <code> - change language
        if (args.length == 1) {
            String lang = args[0].toLowerCase();
            if (!VALID_LANGS.contains(lang)) {
                player.sendMessage(Component.text(
                        Messages.UNKNOWN_LANG.get(info.getLangCode()),
                        NamedTextColor.RED
                ));
                return true;
            }

            info.setLangCode(lang);
            info.setParticlesEnabled(true);

            // Map lang -> a reasonable country code for flag
            String flagCountry = switch (lang) {
                case "fr" -> "FR";
                case "en" -> "GB";
                case "es" -> "ES";
                case "de" -> "DE";
                case "pt" -> "PT";
                case "it" -> "IT";
                case "ja" -> "JP";
                case "ru" -> "RU";
                default -> info.getCountryCode();
            };
            info.setCountryCode(flagCountry);

            showLangTitle(player, info);
            player.sendMessage(Component.text(
                    Messages.LANG_CHANGED.get(lang),
                    NamedTextColor.GREEN
            ));
            return true;
        }

        // /lang (no args) - show current language + welcome title
        showLangTitle(player, info);
        player.sendMessage(Component.text(
                Messages.LANG_CURRENT.get(info.getLangCode()),
                NamedTextColor.AQUA
        ));

        if (!info.isParticlesEnabled()) {
            info.setParticlesEnabled(true);
            player.sendMessage(Component.text(
                    Messages.PARTICLES_ON.get(info.getLangCode()),
                    NamedTextColor.GREEN
            ));
        }

        return true;
    }

    private void showLangTitle(Player player, PlayerData.PlayerInfo info) {
        String lang = info.getLangCode();

        Component title = Component.text(Messages.WELCOME.get(lang))
                .color(NamedTextColor.GOLD)
                .decoration(TextDecoration.BOLD, true);

        Component subtitle = Component.text(Messages.WELCOME_SUBTITLE.get(lang))
                .color(NamedTextColor.YELLOW);

        Title.Times times = Title.Times.times(
                Duration.ofMillis(500),   // fade in
                Duration.ofMillis(3000),  // stay
                Duration.ofMillis(500)    // fade out
        );

        player.showTitle(Title.title(title, subtitle, times));
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
                                      @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            String input = args[0].toLowerCase();
            List<String> options = new java.util.ArrayList<>(VALID_LANGS.stream().sorted().toList());
            options.add("on");
            options.add("off");
            return options.stream()
                    .filter(s -> s.startsWith(input))
                    .toList();
        }
        return List.of();
    }
}
