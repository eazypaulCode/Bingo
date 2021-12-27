package de.eazypaulcode.commands;

import de.eazypaulcode.util.StringDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author eazypaul
 * @since 23.12.2021
 */
public class TopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO: Check GameState
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringDefaults.ERROR("Top") + "§7Du musst ein §9Spieler §7sein.");
            return true;
        }
        if (args.length == 0) {
            if (player.getWorld().getName().equals("world")) {
                World world = Bukkit.getWorld("world");
                if (world == null) {
                    player.sendMessage(StringDefaults.ERROR("Top") + "§7Konnte dich nicht teleportieren.");
                    return true;
                }
                Block block = world.getHighestBlockAt(player.getLocation());
                Location location = block.getLocation();
                location.setY(location.getY() + 1);
                location.setX(location.getBlockX() + 0.500);
                location.setZ(location.getBlockZ() + 0.500);
                int locationY = location.getBlockY();
                if (locationY < 35) {
                    player.sendMessage(StringDefaults.WARNING("Top") + "§7Die neue Höhe ist sehr tief.");
                    player.sendMessage(StringDefaults.NORMAL("Top") + "§7Wenn du dich trotzdem teleportieren möchtest, verwende§8: §9/top confirm");
                    return true;
                }
                if (locationY > 100) {
                    player.sendMessage(StringDefaults.WARNING("Top") + "§7Die neue Höhe ist sehr hoch.");
                    player.sendMessage(StringDefaults.NORMAL("Top") + "§7Wenn du dich trotzdem teleportieren möchtest, verwende§8: §9/top confirm");
                    return true;
                }
                int playerY = player.getLocation().getBlockY();
                if (locationY == 0) {
                    player.sendMessage(StringDefaults.ERROR("Top") + "§7Konnte dich nicht teleportieren.");
                    return true;
                }
                if (locationY == playerY) {
                    player.sendMessage(StringDefaults.ERROR("Top") + "§7Du bist bereits an der Oberfläche.");
                    player.sendMessage(StringDefaults.NORMAL("Top") + "§7Um dich an den Spawn zu teleportieren, verwende§8: §9/spawn");
                    return true;
                }
                player.teleport(location);
                player.sendMessage(StringDefaults.SUCCESS("Top") + "§7Du wurdest an die Oberfläche teleportiert. Y: §9" + locationY);
                return true;
            }
            World world = Bukkit.getWorld("world");
            if (world == null) {
                player.sendMessage(StringDefaults.ERROR("Top") + "§7Konnte dich nicht teleportieren.");
                return true;
            }
            Location location = world.getSpawnLocation();
            player.teleport(location);
            player.sendMessage(StringDefaults.SUCCESS("Top") + "§7Du wurdest in die Overworld teleportiert.");
            return false;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("confirm")) {
                if (player.getWorld().getName().equals("world")) {
                    World world = Bukkit.getWorld("world");
                    if (world == null) {
                        player.sendMessage(StringDefaults.ERROR("Top") + "§7Konnte dich nicht teleportieren.");
                        return true;
                    }
                    Block block = world.getHighestBlockAt(player.getLocation());
                    Location location = block.getLocation();
                    location.setY(location.getY() + 1);
                    location.setX(location.getBlockX() + 0.500);
                    location.setZ(location.getBlockZ() + 0.500);
                    int locationY = location.getBlockY();
                    int playerY = player.getLocation().getBlockY();
                    if (locationY == 0) {
                        player.sendMessage(StringDefaults.ERROR("Top") + "§7Konnte dich nicht teleportieren.");
                        return true;
                    }
                    if (locationY == playerY) {
                        player.sendMessage(StringDefaults.ERROR("Top") + "§7Du bist bereits an der Oberfläche.");
                        return true;
                    }
                    player.teleport(location);
                    player.sendMessage(StringDefaults.SUCCESS("Top") + "§7Du wurdest an die Oberfläche teleportiert. Y: §9" + locationY);
                    return true;
                }
                World world = Bukkit.getWorld("world");
                if (world == null) {
                    player.sendMessage(StringDefaults.ERROR("Top") + "§7Konnte dich nicht teleportieren.");
                    return true;
                }
                Location location = world.getSpawnLocation();
                player.teleport(location);
                player.sendMessage(StringDefaults.SUCCESS("Top") + "§7Du wurdest in die Overworld teleportiert.");
                return false;
            }
        }
        return true;
    }
}
