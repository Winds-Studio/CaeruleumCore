package cn.dreeam.caeruleum.listener;

import cn.dreeam.caeruleum.utils.PermUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Set;
import java.util.UUID;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        String locale = e.getPlayer().locale().toString();
        UUID uuid = e.getPlayer().getUniqueId();

        Set<String> langPerm = PermUtil.getLangPerm(uuid);

        if (!PermUtil.hasLangPerm(langPerm) && !langPerm.contains(locale)) {
            PermUtil.applyLangPerm(uuid, locale);
        }
    }
}
