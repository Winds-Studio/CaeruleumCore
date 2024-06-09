package cn.dreeam.caeruleum.listener;

import cn.dreeam.caeruleum.utils.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Util.clearPlayerRateLimit(e.getPlayer().getUniqueId());
    }
}
