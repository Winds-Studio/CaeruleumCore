package cn.dreeam.caeruleum.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLocaleChangeEvent;

public class LocaleChange implements Listener {

    @EventHandler
    public void onLocaleChange(PlayerLocaleChangeEvent e) {
        Player p = e.getPlayer();
        String locale = e.locale().toString();
    }
}
