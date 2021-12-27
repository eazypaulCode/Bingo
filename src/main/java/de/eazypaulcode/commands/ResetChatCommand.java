package de.eazypaulcode.commands;

import de.eazypaulcode.util.StringDefaults;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author eazypaul
 * @since 24.12.2021
 */
public class ResetChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringDefaults.ERROR("ResetChat") + "§7Du musst ein §9Spieler §7sein.");
            return true;
        }
        for (int i = 0; i < 150; i++) {
            player.sendMessage(" ");
        }
        return false;
    }
}
