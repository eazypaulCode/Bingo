package de.eazypaulcode.goal;

import org.bukkit.Material;

/**
 * @author eazypaul
 * @since 23.12.2021
 */
public class BingoGoalBuilder {

    private String name;
    private GoalType goalType;
    private Material icon;
    private GoalDifficulty difficulty;
    private int id;
    private boolean nether;

    public BingoGoalBuilder(String name) {
        this.name = name;
    }

    public void setDifficulty(GoalDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setGoalType(GoalType goalType) {
        this.goalType = goalType;
    }

    public void setIcon(Material icon) {
        this.icon = icon;
    }

    public void setNether(boolean nether) {
        this.nether = nether;
    }

    public BingoGoal create() {
        if (goalType == GoalType.ITEM) {
            name = icon.toString();
        }
        if (name.isEmpty()) {

        }
        return new BingoGoal(name, goalType, icon, difficulty, id, nether);
    }
}
