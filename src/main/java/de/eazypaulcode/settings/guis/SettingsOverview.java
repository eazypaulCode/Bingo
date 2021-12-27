package de.eazypaulcode.settings.guis;

import de.eazypaulcode.Bingo;
import de.eazypaulcode.util.ItemBuilder;
import de.eazypaulcode.util.StringDefaults;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

/**
 * @author eazypaul
 * @since 24.12.2021
 */
public class SettingsOverview {

    public static void open(Player player) {
        if (player == null) return;
        if (!player.hasPermission("bingo.admin")) return;

        SmartInventory.builder()
                .manager(Bingo.getInstance().getInventoryManager())
                .size(3, 9)
                .title("§9Bingo §7□ §9Übersicht")
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents inventoryContents) {
                        inventoryContents.fill(ClickableItem.of(new ItemBuilder(
                                Material.GRAY_STAINED_GLASS_PANE)
                                .setDisplayName("§7-/-")
                                .addLoreLine(" ")
                                .addLoreLine("§7Klicke, um das GUI zu schließen.")
                                .addLoreLine(" ")
                                .build(), event -> player.closeInventory()));
                        inventoryContents.set(1, 2, ClickableItem.of(new ItemBuilder(
                                        Material.BOOK)
                                        .setDisplayName("§aZiele")
                                        .addLoreLine(" ")
                                        .addLoreLine(" §a§nZiele§r §7sind die")
                                        .addLoreLine(" §a§nAufgaben§r §7von Bingo.")
                                        .addLoreLine(" §7Diese geben verschiedene")
                                        .addLoreLine(" §7Punkte, die die Platzierung")
                                        .addLoreLine(" §7des §bTeams §7bestimmen.")
                                        .addLoreLine(" ")
                                        .addLoreLine("§a§oKlick§r§8: §aZiel-Einstellungen öffnen")
                                        .addLoreLine(" ")
                                        .build(), event -> {
                                    // TODO: Open Goals
                                }
                        ));
                        inventoryContents.set(1, 3, ClickableItem.of(new ItemBuilder(
                                        Material.RED_BED)
                                        .setDisplayName("§bTeams")
                                        .addLoreLine(" ")
                                        .addLoreLine(" §b§nTeams§r §7sind die")
                                        .addLoreLine(" §b§nSpielergruppen§r §7von Bingo.")
                                        .addLoreLine(" §7Alle Spieler in einem")
                                        .addLoreLine(" §b§nTeam§r §7teilen sich die Punkte.")
                                        .addLoreLine(" ")
                                        .addLoreLine("§b§oKlick§r§8: §bTeam-Einstellungen öffnen")
                                        .addLoreLine(" ")
                                        .build(), event -> {
                                    // TODO: Open Teams
                                }
                        ));
                        inventoryContents.set(1, 5, ClickableItem.of(new ItemBuilder(
                                        Material.COMMAND_BLOCK)
                                        .setDisplayName("§cAllgemeine Einstellungen")
                                        .addLoreLine(" ")
                                        .addLoreLine(" §c§nAllgemeine Einstellungen§r §7stellen das")
                                        .addLoreLine(" §c§nSpielgeschehen§r §7von Bingo ein.")
                                        .addLoreLine(" ")
                                        .addLoreLine("§c§oKlick§r§8: §cAllgemeine Einstellungen öffnen")
                                        .addLoreLine("§c§oStrg-Item fallenlassen§r§8: §cStandardeinstellungen")
                                        .addLoreLine(" ")
                                        .build(), event -> {
                                    if (event.getClick() == ClickType.CONTROL_DROP) {
                                        event.getWhoClicked().sendMessage(StringDefaults.SUCCESS("Bingo") + "§cAllgemeine Einstellungen §7zurückgesetzt.");
                                        Bingo.getInstance().getSettingsManager().resetGeneral();
                                        return;
                                    }
                                    GeneralSettings.open(player);
                                }
                        ));
                    }

                    @Override
                    public void update(Player player, InventoryContents inventoryContents) {

                    }
                }).build().open(player);
    }

}
