package me.grovre.playtimetracker.listeners;

import me.grovre.playtimetracker.PlaytimeTracker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    @EventHandler
    public void OnPlayerLeaveServer(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        long leaveTime = System.currentTimeMillis();

        long totalSessionTime = leaveTime - PlaytimeTracker.playerSessionTimes.get(player.getUniqueId());
        PlaytimeTracker.playerTotalTimes.merge(player.getUniqueId(), totalSessionTime, Long::sum);
    }
}
