package de.eazypaulcode;

import de.eazypaulcode.commands.*;
import de.eazypaulcode.goal.GoalConfiguration;
import de.eazypaulcode.listeners.InteractListener;
import de.eazypaulcode.listeners.JoinListener;
import de.eazypaulcode.settings.SettingsManager;
import de.eazypaulcode.tabcompleter.ItemTabCompleter;
import de.eazypaulcode.tabcompleter.SaveWorldTabCompleter;
import de.eazypaulcode.tabcompleter.WorldTabCompleter;
import fr.minuskube.inv.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bingo extends JavaPlugin {

    private static Bingo plugin;

    private GoalConfiguration goalConfiguration;
    private SettingsManager settingsManager;
    private InventoryManager inventoryManager;

    public static Bingo getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        init();
    }

    private void init() {
        registerManagers();
        registerCommands();
        registerListeners();
        initManagers();
        announceLoad();
    }

    private void registerManagers() {
        inventoryManager = new InventoryManager(this);
        settingsManager = new SettingsManager();
        goalConfiguration = new GoalConfiguration();
    }

    private void initManagers() {
        inventoryManager.init();
        goalConfiguration.create();
    }

    private void registerCommands() {
        getCommand("start").setExecutor(new StartCommand());
        getCommand("top").setExecutor(new TopCommand());
        getCommand("item").setExecutor(new ItemCommand());
        getCommand("item").setTabCompleter(new ItemTabCompleter());
        getCommand("suicide").setExecutor(new SuicideCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("world").setExecutor(new WorldCommand());
        getCommand("world").setTabCompleter(new WorldTabCompleter());
        getCommand("creeper").setExecutor(new CreeperCommand());
        getCommand("settings").setExecutor(new SettingsCommand());
        getCommand("clearchat").setExecutor(new ClearChatCommand());
        getCommand("resetchat").setExecutor(new ResetChatCommand());
        getCommand("saveworld").setExecutor(new SaveWorldCommand());
        getCommand("saveworld").setTabCompleter(new SaveWorldTabCompleter());
    }

    private void registerListeners() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinListener(), this);
        manager.registerEvents(new InteractListener(), this);
    }

    private void announceLoad() {
        Bukkit.getLogger().info(ChatColor.GREEN + "Bingo wurde erfolreich geladen\n");
        Bukkit.getLogger().info(ChatColor.AQUA + "  ____  _                   ");
        Bukkit.getLogger().info(ChatColor.AQUA + " |  _ \\(_)                  ");
        Bukkit.getLogger().info(ChatColor.AQUA + " | |_) |_ _ __   __ _  ___  ");
        Bukkit.getLogger().info(ChatColor.AQUA + " |  _ <| | '_ \\ / _` |/ _ \\ ");
        Bukkit.getLogger().info(ChatColor.AQUA + " | |_) | | | | | (_| | (_) |");
        Bukkit.getLogger().info(ChatColor.AQUA + " |____/|_|_| |_|\\__, |\\___/ ");
        Bukkit.getLogger().info(ChatColor.AQUA + "                 __/ |      ");
        Bukkit.getLogger().info(ChatColor.AQUA + "                |___/       \n");
        Bukkit.getLogger().info(ChatColor.GRAY + "Server Vers. " + ChatColor.AQUA + Bukkit.getServer().getBukkitVersion().split("-")[0] + ChatColor.DARK_GRAY + " | " + ChatColor.GRAY + "Developer" + ChatColor.AQUA + " eazypaulcode" + ChatColor.DARK_GRAY + " | " + ChatColor.AQUA + "https://github.com/eazyPaul/" + ChatColor.DARK_GRAY + " | " + ChatColor.RED + "Vielen Dank fürs Benutzen\n");
    }

    public GoalConfiguration getGoalConfiguration() {
        return goalConfiguration;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }

    @Override
    public void onDisable() {

    }
}
