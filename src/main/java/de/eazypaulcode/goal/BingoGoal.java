package de.eazypaulcode.goal;

import org.bukkit.Material;

/**
 * @author eazypaul
 * @since 23.12.2021
 */
public class BingoGoal {

    private final String name;
    private final GoalType goalType;
    private final Material icon;
    private final GoalDifficulty difficulty;
    private final int id;
    private final boolean nether;

    public BingoGoal(String name, GoalType goalType, Material icon, GoalDifficulty difficulty, int id, boolean nether) {
        this.name = name;
        this.goalType = goalType;
        this.icon = icon;
        this.difficulty = difficulty;
        this.id = id;
        this.nether = nether;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public GoalDifficulty getDifficulty() {
        return difficulty;
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public Material getIcon() {
        return icon;
    }
}
