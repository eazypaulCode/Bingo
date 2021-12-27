package de.eazypaulcode.commands;

import de.eazypaulcode.util.StringDefaults;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author eazypaul
 * @since 23.12.2021
 */
public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(StringDefaults.ERROR("Spawn") + "§7Du musst ein §9Spieler §7sein.");
            return true;
        }
        Player player = (Player) sender;
        World world = Bukkit.getWorld("world");
        if (world == null) {
            player.sendMessage(StringDefaults.ERROR("Spawn") + "§7Du konntest nicht an den Worldspawn teleportiert werden.");
            return true;
        }
        String playerWorldName = player.getWorld().getName();
        if (playerWorldName.equals("world")) {
            if (player.getLocation().distance(world.getSpawnLocation()) < 1) {
                player.sendMessage(StringDefaults.ERROR("Spawn") + "§7Du befindest dich bereits am Spawn.");
                return true;
            }
            player.teleport(world.getSpawnLocation());
            player.sendMessage(StringDefaults.SUCCESS("Spawn") + "§7Du wurdest an den Worldspawn teleportiert.");
            return true;
        }
        player.teleport(world.getSpawnLocation());
        player.sendMessage(StringDefaults.SUCCESS("Spawn") + "§7Du wurdest an den Worldspawn der Overworld teleportiert.");
        return false;
    }
}
