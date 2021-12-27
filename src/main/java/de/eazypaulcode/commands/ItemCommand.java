package de.eazypaulcode.commands;

import de.eazypaulcode.util.ItemUtils;
import de.eazypaulcode.util.StringDefaults;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

/**
 * @author eazypaul
 * @since 23.12.2021
 */
public class ItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("bingo.admin")) return true;
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringDefaults.ERROR("Item") + "§7Du musst ein §9Spieler §7sein.");
            return true;
        }
        if (args.length == 1) {
            String input = args[0].toUpperCase(Locale.ROOT);
            Material material = Material.getMaterial(input);
            if (material == null) {
                player.sendMessage(StringDefaults.ERROR("Item") + "§7Dieses Item existiert nicht.");
                return true;
            }
            if (!material.isItem()) {
                player.sendMessage(StringDefaults.ERROR("Item") + "§7Dieses Item kann nicht vergeben werden.");
                return true;
            }
            if (material.isAir()) {
                player.sendMessage(StringDefaults.ERROR("Item") + "§7Dieses Item ist Luft.");
                return true;
            }
            if (player.getInventory().firstEmpty() == -1) {
                player.sendMessage(StringDefaults.ERROR("Item") + "§7Dein Inventar ist voll.");
                return true;
            }
            player.getInventory().addItem(new ItemStack(material));
            player.sendMessage(StringDefaults.SUCCESS("Item") + "§7Du hast §9" + ItemUtils.getName(material) + " §7erhalten.");
            return true;
        }
        player.sendMessage(StringDefaults.ERROR("Item") + "§7Bitte benutze§8: §9/item <Item>");
        return false;
    }
}
