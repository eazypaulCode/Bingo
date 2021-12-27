package de.eazypaulcode.settings.guis;

import de.eazypaulcode.Bingo;
import de.eazypaulcode.settings.SettingsManager;
import de.eazypaulcode.util.ItemBuilder;
import de.eazypaulcode.util.StringDefaults;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;


/**
 * @author eazypaul
 * @since 24.12.2021
 */
public class GeneralSettings {

    public static void open(Player player) {
        if (player == null) return;
        if (!player.hasPermission("bingo.admin")) return;
        SmartInventory.builder()
                .manager(Bingo.getInstance().getInventoryManager())
                .size(3, 9)
                .title("§9Bingo §7□ §cAllgemeine Einstellungen")
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents inventoryContents) {
                        inventoryContents.fill(ClickableItem.of(new ItemBuilder(
                                Material.GRAY_STAINED_GLASS_PANE)
                                .setDisplayName("§7-/-")
                                .addLoreLine(" ")
                                .addLoreLine("§7Klicke, um das zurückzugelangen.")
                                .addLoreLine(" ")
                                .build(), event -> SettingsOverview.open(player)));
                        setContents(inventoryContents, player);
                    }

                    @Override
                    public void update(Player player, InventoryContents inventoryContents) {
                        setContents(inventoryContents, player);
                    }
                }).build().open(player);
    }

    private static void setContents(InventoryContents inventoryContents, Player user) {
        inventoryContents.set(1, 1, ClickableItem.of(new ItemBuilder(
                Bingo.getInstance().getSettingsManager().isDamage() ? Material.DIAMOND_SWORD : Material.WOODEN_SWORD)
                .setDisplayName("§cSchaden")
                .addLoreLine(" ")
                .addLoreLine(" §9§nBeschreibung§r§8:")
                .addLoreLine("  §7Diese Einstellung")
                .addLoreLine("  §9(de)aktiviert Schaden§7.")
                .addLoreLine(" ")
                .addLoreLine(" §9§nEigenschaften§r§8:")
                .addLoreLine("  §7Typ§8: §cAllgemeine Einstellung")
                .addLoreLine(Bingo.getInstance().getSettingsManager().isDamage() ? "  §7Status§8: §2Schaden aktiviert" : "  §7Status§8: §4Schaden deaktiviert")
                .addLoreLine(" ")
                .addLoreLine("§c§oKlick§r§8: §9Status ändern")
                .addLoreLine("§c§oStrg-Item fallenlassen§r§8: §9Zurücksetzen")
                .addLoreLine(" ")
                .addFlags(ItemFlag.HIDE_ATTRIBUTES)
                .build(),
                event -> {
                    if (event.getClick() == ClickType.CONTROL_DROP) {
                        event.getWhoClicked().sendMessage(StringDefaults.SUCCESS("Bingo") + "§cSchaden §7zurückgesetzt.");
                        Bingo.getInstance().getSettingsManager().setDamage(true);
                        return;
                    }
                    Bingo.getInstance().getSettingsManager().setDamage(!Bingo.getInstance().getSettingsManager().isDamage());
                }));
        inventoryContents.set(1, 2, ClickableItem.of(new ItemBuilder(
                        Bingo.getInstance().getSettingsManager().isHunger() ? Material.MUTTON : Material.COOKED_MUTTON)
                        .setDisplayName("§cHunger")
                        .addLoreLine(" ")
                        .addLoreLine(" §9§nBeschreibung§r§8:")
                        .addLoreLine("  §7Diese Einstellung")
                        .addLoreLine("  §9(de)aktiviert Hunger§7.")
                        .addLoreLine(" ")
                        .addLoreLine(" §9§nEigenschaften§r§8:")
                        .addLoreLine("  §7Typ§8: §cAllgemeine Einstellung")
                        .addLoreLine(Bingo.getInstance().getSettingsManager().isHunger() ? "  §7Status§8: §2Hunger aktiviert" : "  §7Status§8: §4Hunger deaktiviert")
                        .addLoreLine(" ")
                        .addLoreLine("§c§oKlick§r§8: §9Status ändern")
                        .addLoreLine("§c§oStrg-Item fallenlassen§r§8: §9Zurücksetzen")
                        .addLoreLine(" ")
                        .addFlags(ItemFlag.HIDE_ATTRIBUTES)
                        .build(),
                event -> {
                    if (event.getClick() == ClickType.CONTROL_DROP) {
                        event.getWhoClicked().sendMessage(StringDefaults.SUCCESS("Bingo") + "§cHunger §7zurückgesetzt.");
                        Bingo.getInstance().getSettingsManager().setHunger(true);
                        return;
                    }
                    Bingo.getInstance().getSettingsManager().setHunger(!Bingo.getInstance().getSettingsManager().isHunger());
                }));
        inventoryContents.set(1, 3, ClickableItem.of(new ItemBuilder(
                        Bingo.getInstance().getSettingsManager().isBackpackEnabled() ? Material.DIAMOND_CHESTPLATE : Material.LEATHER_CHESTPLATE)
                        .setDisplayName("§cRucksack")
                        .addLoreLine(" ")
                        .addLoreLine(" §9§nBeschreibung§r§8:")
                        .addLoreLine("  §7Diese Einstellung")
                        .addLoreLine("  §9(de)aktiviert §7den §9Rucksack§7.")
                        .addLoreLine(" ")
                        .addLoreLine(" §9§nEigenschaften§r§8:")
                        .addLoreLine("  §7Typ§8: §cAllgemeine Einstellung")
                        .addLoreLine(Bingo.getInstance().getSettingsManager().isBackpackEnabled() ? "  §7Status§8: §2Rucksack aktiviert" : "  §7Status§8: §4Rucksack deaktiviert")
                        .addLoreLine(" ")
                        .addLoreLine("§c§oKlick§r§8: §9Status ändern")
                        .addLoreLine("§c§oStrg-Item fallenlassen§r§8: §9Zurücksetzen")
                        .addLoreLine(" ")
                        .addFlags(ItemFlag.HIDE_ATTRIBUTES)
                        .build(),
                event -> {
                    if (event.getClick() == ClickType.CONTROL_DROP) {
                        event.getWhoClicked().sendMessage(StringDefaults.SUCCESS("Bingo") + "§cRucksack §7zurückgesetzt.");
                        Bingo.getInstance().getSettingsManager().setBackpackEnabled(false);
                        return;
                    }
                    Bingo.getInstance().getSettingsManager().setBackpackEnabled(!Bingo.getInstance().getSettingsManager().isBackpackEnabled());
                }));
        inventoryContents.set(1, 4, ClickableItem.of(new ItemBuilder(
                        Bingo.getInstance().getSettingsManager().isBorder() ? Material.STRUCTURE_VOID : Material.BARRIER)
                        .setDisplayName("§cBorder")
                        .addLoreLine(" ")
                        .addLoreLine(" §9§nBeschreibung§r§8:")
                        .addLoreLine("  §7Diese Einstellung")
                        .addLoreLine("  §7stellt die §9Border §7ein.")
                        .addLoreLine(" ")
                        .addLoreLine(" §9§nEigenschaften§r§8:")
                        .addLoreLine("  §7Typ§8: §cAllgemeine Einstellung")
                        .addLoreLine(Bingo.getInstance().getSettingsManager().isBorder() ? "  §7Status§8: §2Border aktiviert" : "  §7Status§8: §4Border deaktiviert")
                        .addLoreLine("  §7Bordergröße§8: §9" + Bingo.getInstance().getSettingsManager().getBorderSize())
                        .addLoreLine(" ")
                        .addLoreLine(user.getGameMode() == GameMode.CREATIVE ? "§c§oMittlere Maustaste§r§8: §9Status ändern" : "§c§oItem fallenlassen§r§8: §9Status ändern")
                        .addLoreLine("§c§oLinksklick§r§8: §9Border um 1000 vergrößern")
                        .addLoreLine("§c§oRechtsklick§r§8: §9Border um 1000 verkleinern")
                        .addLoreLine("§c§oStrg-Item fallenlassen§r§8: §9Zurücksetzen")
                        .addLoreLine(" ")
                        .addFlags(ItemFlag.HIDE_ATTRIBUTES)
                        .build(),
                event -> {
                    if (event.getClick() == ClickType.CONTROL_DROP) {
                        event.getWhoClicked().sendMessage(StringDefaults.SUCCESS("Bingo") + "§cBorder §7zurückgesetzt.");
                        Bingo.getInstance().getSettingsManager().setBorder(true);
                        Bingo.getInstance().getSettingsManager().setBorderSize(10000);
                        return;
                    }
                    Player player = (Player) event.getWhoClicked();
                    if (event.getClick() == ClickType.MIDDLE || event.getClick() == ClickType.DROP) {
                        Bingo.getInstance().getSettingsManager().setBorder(!Bingo.getInstance().getSettingsManager().isBorder());
                        return;
                    }
                    if (!Bingo.getInstance().getSettingsManager().isBorder()) {
                        player.sendMessage(StringDefaults.ERROR("Bingo") + "§7Die §cBorder §7muss aktiviert sein.");
                        return;
                    }
                    if (event.getClick() == ClickType.RIGHT || event.getClick() == ClickType.SHIFT_RIGHT) {
                            if (Bingo.getInstance().getSettingsManager().getBorderSize() <= 5000) {
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
                                player.sendMessage(StringDefaults.ERROR("Bingo") + "§7Die §cBorder §7muss mindestens §95000 Blöcke §7groß sein.");
                                return;
                            }
                            Bingo.getInstance().getSettingsManager().setBorderSize(Bingo.getInstance().getSettingsManager().getBorderSize() - 1000);
                    }
                    if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.SHIFT_LEFT) {
                        if (Bingo.getInstance().getSettingsManager().getBorderSize() >= 100000) {
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
                            player.sendMessage(StringDefaults.ERROR("Bingo") + "§7Die §cBorder §7darf maximal §9100000 Blöcke §7groß sein.");
                            return;
                        }
                        Bingo.getInstance().getSettingsManager().setBorderSize(Bingo.getInstance().getSettingsManager().getBorderSize() + 1000);
                    }
                }));
    }

}
