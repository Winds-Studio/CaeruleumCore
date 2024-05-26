package cn.dreeam.caeruleum.listener;

import cn.dreeam.caeruleum.utils.PermUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLocaleChangeEvent;

import java.util.Set;
import java.util.UUID;

public class LocaleChange implements Listener {

    @EventHandler
    public void onLocaleChange(PlayerLocaleChangeEvent e) {
        // Should add threshold to prevent lag
        String locale = e.getPlayer().locale().toString();
        UUID uuid = e.getPlayer().getUniqueId();

        Set<String> langPerm = PermUtil.getLangPerm(uuid);

        if (!PermUtil.hasLangPerm(langPerm) && !langPerm.contains(locale)) {
            PermUtil.applyLangPerm(uuid, locale);
        }
    }
}
