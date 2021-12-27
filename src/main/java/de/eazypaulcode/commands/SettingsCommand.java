package de.eazypaulcode.commands;

import de.eazypaulcode.settings.guis.SettingsOverview;
import de.eazypaulcode.util.StringDefaults;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author eazypaul
 * @since 24.12.2021
 */
public class SettingsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringDefaults.ERROR("Bingo") + "§7Du musst ein §9Spieler §7sein.");
            return true;
        }
        if (!sender.hasPermission("bingo.admin")) return true;
        SettingsOverview.open(player);
        player.sendMessage(StringDefaults.SUCCESS("Bingo") + "§7Du hast das Einstellungsmenü geöffnet.");
        return false;
    }
}
