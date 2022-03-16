package me.grovre.playtimetracker;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class PlaytimeTracker extends JavaPlugin {

    public static PlaytimeTracker plugin;
    public static HashMap<UUID, Long> playerSessionTimes;
    public static HashMap<UUID, Long> playerTotalTimes;

    public static PlaytimeTracker getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        playerSessionTimes = new HashMap<>();
        playerTotalTimes = new dbUtil().loadEntries();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        new dbUtil().saveEntries(playerTotalTimes);
    }
}
