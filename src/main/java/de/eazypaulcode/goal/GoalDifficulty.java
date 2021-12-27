package de.eazypaulcode.goal;

import org.bukkit.ChatColor;

public enum GoalDifficulty {

    VERY_EASY(1, ChatColor.AQUA),
    EASY(2, ChatColor.GREEN),
    NORMAL(3, ChatColor.YELLOW),
    HARD(5, ChatColor.RED),
    HARDER(10, ChatColor.DARK_RED),
    EXTREME(15, ChatColor.DARK_RED);

    private final int points;
    private final ChatColor color;

    GoalDifficulty(int points, ChatColor color) {
        this.points = points;
        this.color = color;
    }

    public int getPoints() {
        return points;
    }

    public ChatColor getColor() {
        return color;
    }
}
