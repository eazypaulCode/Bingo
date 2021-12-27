package de.eazypaulcode.listeners;

import de.eazypaulcode.Bingo;
import de.eazypaulcode.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author eazypaul
 * @since 23.12.2021
 */
public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.getPlayer().setPlayerListHeader("\n§9§lBingo\n§7Version§8: §9" + Bingo.getInstance().getDescription().getVersion() + " \n§7by §9eazypaulcode§8.\n");
        event.getPlayer().setPlayerListFooter("\n§5Twitch§8: §5twitch.tv/eazypaulcode\n§9Discord§8: §9eazypaulcode#0001\n");
        if (player.hasPermission("bingo.admin")) {
            event.setJoinMessage("§7[§a»§7] §d" + player.getName());
            event.getPlayer().setPlayerListName("§dBingo-Admin §7| §d" + player.getName());
            event.getPlayer().setDisplayName("§d" + player.getName());
            player.getInventory().setItem(4, new ItemBuilder(
                    Material.COMMAND_BLOCK)
                    .setDisplayName("§9Einstellungen §7(Rechtsklick)")
                    .addLoreLine(" ")
                    .addLoreLine("§9Rechtsklicke§7, um das")
                    .addLoreLine("§7Bingo-Einstellungsmenü")
                    .addLoreLine("§7zu öffnen.")
                    .addLoreLine(" ")
                    .build()
            );
            player.getInventory().setItem(8, new ItemBuilder(
                    Material.GREEN_DYE)
                    .setDisplayName("§aSpiel starten §7(Rechtsklick)")
                    .addLoreLine(" ")
                    .addLoreLine("§9Rechtsklicke§7, um das")
                    .addLoreLine("§7Spiel in 10 Sekunden")
                    .addLoreLine("§7zu starten.")
                    .addLoreLine(" ")
                    .build()
            );
        } else {
            event.setJoinMessage("§7[§a»§7] " + player.getName());
            event.getPlayer().setPlayerListName("§7Bingo-Spieler | " + player.getName());
            event.getPlayer().setDisplayName("§7" + player.getName());
            player.getInventory().setItem(4, new ItemBuilder(
                    Material.NETHER_STAR)
                    .setDisplayName("§9Spielprinzip §7(Rechtsklick)")
                    .addLoreLine(" ")
                    .addLoreLine("§9Rechtsklicke§7, um das")
                    .addLoreLine("§7Spielprinzip anzuzeigen.")
                    .addLoreLine(" ")
                    .build()
            );
        }
    }

}
