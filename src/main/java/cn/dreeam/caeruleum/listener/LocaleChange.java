package cn.dreeam.caeruleum.listener;

import cn.dreeam.caeruleum.CaeruleumCore;
import cn.dreeam.caeruleum.utils.PermUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLocaleChangeEvent;

import java.util.List;
import java.util.UUID;

public class LocaleChange implements Listener {

    @EventHandler
    public void onLocaleChange(PlayerLocaleChangeEvent e) {
        // Should add threshold to prevent lag Or use async queue to execute all logic.
        String locale = e.locale().toString();
        UUID uuid = e.getPlayer().getUniqueId();
        List<String> langPerms = PermUtil.getLangPerm(uuid);

        if (langPerms.size() > 1) {
            PermUtil.clearLangPerm(uuid);
        }

        if (CaeruleumCore.config.mainLocale().contains(locale)) {
            // Return or give default/fallback
            return;
        }

        List<String> localeBlackWhiteList = CaeruleumCore.config.localeBlackWhiteList();
        boolean useWhitelist = CaeruleumCore.config.localeBlackWhiteListUseWhitelist();

        if ((useWhitelist && !localeBlackWhiteList.contains(locale))
                || (!useWhitelist && localeBlackWhiteList.contains(locale))) {
            locale = CaeruleumCore.config.localeBlackWhiteListFallback();
        }

        String langPermNode = CaeruleumCore.config.langPermKeyPrefix() + locale;

        if (PermUtil.hasLangPerm(langPerms)) {
            if (!langPerms.getFirst().equals(langPermNode)) {
                PermUtil.modifyLangPerm(uuid, langPermNode);
            }
        } else {
            PermUtil.applyLangPerm(uuid, langPermNode);
        }
    }
}
