package de.eazypaulcode.util;

import org.bukkit.Material;

import java.util.Locale;

/**
 * @author eazypaul
 * @since 23.12.2021
 */
public class ItemUtils {

    public static String getName(Material material) {
        if (material == null) return "";
        String[] materialNames = material.toString().split("_");
        StringBuilder stringBuilder = new StringBuilder();
        boolean first = true;
        for (int i = 0; i < materialNames.length; i++) {
            if (!first) {
                stringBuilder.append(" ");
            }
            String materialName = materialNames[i];
            stringBuilder.append(materialName.charAt(0));
            stringBuilder.append(materialName.substring(1).toLowerCase(Locale.ROOT));
            first = false;
        }
        return stringBuilder.toString();
    }

}
