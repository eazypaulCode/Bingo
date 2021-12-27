package de.eazypaulcode.commands;

import de.eazypaulcode.util.StringDefaults;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * @author eazypaul
 * @since 24.12.2021
 */
public class ClearChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("bingo.admin")) return true;
        for (int i = 0; i < 150; i++) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("bingo.admin")) continue;
                player.sendMessage(" ");
            }
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(StringDefaults.SUCCESS("Bingo") + "§7Du hast den Chat gecleart.");
            }
            if (player.getName().equals(sender.getName())) {
                player.sendMessage(StringDefaults.SUCCESS("Bingo") + "§7Du hast den Chat gecleart.");
                player.sendMessage(StringDefaults.NORMAL("Bingo") + "§7Der Chat wurde bei dir nicht gecleart, weil du ein Admin bist.");
                return true;
            }
            if (player.hasPermission("bingo.admin")) {
                player.sendMessage(StringDefaults.NORMAL("Bingo") + "§7Der Chat wurde von §9" + sender.getName() + " §7gecleart.");
                player.sendMessage(StringDefaults.NORMAL("Bingo") + "§7Der Chat wurde bei dir nicht gecleart, weil du ein Admin bist.");
                return true;
            }
            Bukkit.broadcastMessage(StringDefaults.NORMAL("Bingo") + "§7Der Chat wurde von §9" + sender.getName() + " §7gecleart.");
        }
        return false;
    }
}
