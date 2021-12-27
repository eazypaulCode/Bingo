package de.eazypaulcode.goal;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * @author eazypaul
 * @since 19.11.2021
 */
public class GoalConfiguration {

    private final File path = new File("./plugins/Bingo/");
    private final File file = new File(path, "goals.yml");
    private YamlConfiguration config;

    public void create() {
        if (!this.path.exists()) this.path.mkdirs();
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void save() {
        try {
            this.config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public YamlConfiguration getConfig() {
        return this.config;
    }

    public String getPath() {
        return this.file.getPath();
    }
}
