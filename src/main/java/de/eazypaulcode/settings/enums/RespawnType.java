package de.eazypaulcode.settings.enums;

import org.bukkit.ChatColor;

public enum RespawnType {

    AIR(ChatColor.AQUA, "Luft"),
    SPAWN(ChatColor.DARK_PURPLE, "Spawn"),
    DISABLED(ChatColor.DARK_RED, "Deaktiviert");

    private ChatColor color;
    private String name;

    RespawnType(ChatColor color, String name) {
        this.color = color;
        this.name = name;
    }
}
