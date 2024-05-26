package cn.dreeam.caeruleum.listener;

import cn.dreeam.caeruleum.utils.PermUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.locale();

        if (PermUtil.hasLangPerm(p.getUniqueId())) {

        } else {

        }
    }
}
