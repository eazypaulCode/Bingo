package de.eazypaulcode.util;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;


    public ItemBuilder(Material material) {
        item = new ItemStack(material);
        meta = item.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        item = new ItemStack(material, amount);
        meta = item.getItemMeta();
    }

    public ItemBuilder setDisplayName(String displayName) {
        assert displayName != null;
        meta.setDisplayName(displayName);
        return this;
    }

    public ItemBuilder setSkullOwner(OfflinePlayer owner) {
        assert owner != null;
        try {
            SkullMeta skullMeta = (SkullMeta) meta;
            skullMeta.setOwningPlayer(owner);
            return this;
        } catch (ClassCastException e) {
            return null;
        }
    }

    public ItemBuilder setLore(String... lore) {
        assert lore != null;
        meta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder addLoreLine(String lore) {
        assert lore != null;
        List<String> loreList;
        if (meta.hasLore() && meta.getLore() != null)
            loreList = new ArrayList<>(meta.getLore());
        else
            loreList = new ArrayList<>();
        loreList.add(lore);
        meta.setLore(loreList);
        return this;
    }

    public ItemBuilder addFlags(ItemFlag flag) {
        meta.addItemFlags(flag);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        assert lore != null;
        meta.setLore(lore);
        return this;
    }

    public ItemBuilder setLocalizedName(String localizedName) {
        assert localizedName != null;
        meta.setLocalizedName(localizedName);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        assert enchantment != null;
        meta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }

}


