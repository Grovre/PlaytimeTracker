package me.grovre.playtimetracker.listeners;

import me.grovre.playtimetracker.PlaytimeTracker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void OnPlayerJoinServer(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        long joinTime = System.currentTimeMillis();

        PlaytimeTracker.playerSessionTimes.put(player.getUniqueId(), joinTime);
    }
}
