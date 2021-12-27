package de.eazypaulcode.commands;

import de.eazypaulcode.util.StringDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author eazypaul
 * @since 23.12.2021
 */
public class CreeperCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("bingo.admin")) return true;
        if (args.length == 0) {
            sender.sendMessage(StringDefaults.ERROR("Creeper") + "§7Bitte benutze§8: §9/creeper <Spieler>");
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            sender.sendMessage(StringDefaults.ERROR("Creeper") + "§7Dieser Spieler ist nicht online.");
            return true;
        }
        if (target.getName().equals(sender.getName())) {
            sender.sendMessage(StringDefaults.WARNING("Creeper") + "§7Wieso willst du dich selber erschrecken?");
            return true;
        }
        sender.sendMessage(StringDefaults.SUCCESS("Creeper") + "§7Du hast nun §9" + target.getName() + " §7erschreckt. :D");
        target.playSound(target.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 1, 1);
        return false;
    }
}
