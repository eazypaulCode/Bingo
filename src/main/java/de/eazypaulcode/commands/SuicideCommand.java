package de.eazypaulcode.commands;

import de.eazypaulcode.util.StringDefaults;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author eazypaul
 * @since 23.12.2021
 */
public class SuicideCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringDefaults.ERROR("Suicide") + "§7Du musst ein §9Spieler §7sein.");
            return true;
        }
        if (args.length == 0) {
            player.setHealth(0);
            player.sendMessage(StringDefaults.SUCCESS("Suicide") + "§7Du hast dich selber getötet.");
            return true;
        }
        if (!player.hasPermission("bingo.admin")) {
            player.setHealth(0);
            player.sendMessage(StringDefaults.SUCCESS("Suicide") + "§7Du hast dich selber getötet.");
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            player.sendMessage(StringDefaults.ERROR("Kill") + "§7Dieser Spieler ist nicht online.");
            return true;
        }
        if (target.getUniqueId() == player.getUniqueId()) {
            player.sendMessage(StringDefaults.ERROR("Kill") + "§7Benutze§8: §9/suicide");
            return true;
        }
        target.setHealth(0);
        player.sendMessage(StringDefaults.SUCCESS("Kill") + "§7Du hast §9" + target.getName() + " §7getötet.");
        target.sendMessage(StringDefaults.NORMAL("Kill") + "§7Du wurdest von §9" + player.getName() + " §7getötet.");
        return false;
    }
}
