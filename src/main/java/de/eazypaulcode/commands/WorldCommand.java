package de.eazypaulcode.commands;

import de.eazypaulcode.util.StringDefaults;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

/**
 * @author eazypaul
 * @since 23.12.2021
 */
public class WorldCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("bingo.admin")) return true;
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringDefaults.ERROR("World") + "§7Du musst ein §9Spieler §7sein.");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(StringDefaults.ERROR("World") + "§7Bitte benutze§8: §9/world <Welt>");
            return true;
        }
        switch (args[0].toUpperCase(Locale.ROOT)) {
            case "OVERWORLD":
            case "WORLD":
                World overworld = Bukkit.getWorld("world");
                if (overworld == null) {
                    player.sendMessage(StringDefaults.ERROR("World") + "§7Konnte dich nicht teleportieren.");
                    return true;
                }
                if (player.getWorld() == overworld) {
                    player.sendMessage(StringDefaults.ERROR("World") + "§7Du bist bereits auf der Overworld.");
                    return true;
                }
                player.teleport(overworld.getSpawnLocation());
                player.sendMessage(StringDefaults.SUCCESS("World") + "§7Du wurdest in die Overworld teleportiert.");
                return true;
            case "NETHER":
            case "WORLD_NETHER":
                World nether = Bukkit.getWorld("world_nether");
                if (nether == null) {
                    player.sendMessage(StringDefaults.ERROR("World") + "§7Konnte dich nicht teleportieren.");
                    return true;
                }
                if (player.getWorld() == nether) {
                    player.sendMessage(StringDefaults.ERROR("World") + "§7Du bist bereits im Nether.");
                    return true;
                }
                player.teleport(nether.getSpawnLocation());
                player.sendMessage(StringDefaults.SUCCESS("World") + "§7Du wurdest in den Nether teleportiert.");
                return true;
            case "END":
            case "WORLD_THE_END":
                World end = Bukkit.getWorld("world_the_end");
                if (end == null) {
                    player.sendMessage(StringDefaults.ERROR("World") + "§7Konnte dich nicht teleportieren.");
                    return true;
                }
                if (player.getWorld() == end) {
                    player.sendMessage(StringDefaults.ERROR("World") + "§7Du bist bereits im End.");
                    return true;
                }
                player.teleport(end.getSpawnLocation());
                player.sendMessage(StringDefaults.SUCCESS("World") + "§7Du wurdest ins End teleportiert.");
                return true;
            default:
                sender.sendMessage(StringDefaults.ERROR("World") + "§9" + args[0] + " §7ist keine gültige Welt.");
                return true;
        }
    }
}
