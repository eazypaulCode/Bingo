package de.eazypaulcode.listeners;

import de.eazypaulcode.settings.guis.SettingsOverview;
import de.eazypaulcode.util.StringDefaults;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author eazypaul
 * @since 24.12.2021
 */
public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (event.getItem().getType().isAir()) return;
        if (!event.getItem().hasItemMeta()) return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getItem().getItemMeta().getDisplayName().equals("§9Einstellungen §7(Rechtsklick)")) {
            if (!event.getPlayer().hasPermission("bingo.admin")) {
                event.getPlayer().getInventory().clear();
                event.getPlayer().sendMessage(StringDefaults.ERROR("Bingo") + "§cDazu bist du nicht berechtigt.");
                return;
            }
            event.setCancelled(true);
            SettingsOverview.open(event.getPlayer());
        }
        if (event.getItem().getItemMeta().getDisplayName().equals("§aSpiel starten §7(Rechtsklick)")) {
            if (!event.getPlayer().hasPermission("bingo.admin")) {
                event.getPlayer().getInventory().remove(event.getItem());
                event.getPlayer().sendMessage(StringDefaults.ERROR("Bingo") + "§cDazu bist du nicht berechtigt.");
                return;
            }
            event.getPlayer().getInventory().remove(event.getItem());
            event.getPlayer().performCommand("start");
            event.setCancelled(true);
        }
    }

}
