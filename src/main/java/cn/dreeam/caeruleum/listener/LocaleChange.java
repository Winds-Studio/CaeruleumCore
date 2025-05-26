package cn.dreeam.caeruleum.listener;

import cn.dreeam.caeruleum.CaeruleumCore;
import cn.dreeam.caeruleum.utils.PermUtil;
import cn.dreeam.caeruleum.utils.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLocaleChangeEvent;

import java.util.List;
import java.util.UUID;

public class LocaleChange implements Listener {

    @EventHandler
    private void onLocaleChange(PlayerLocaleChangeEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();

        // Use threshold limit to prevent lag using packets.
        if (Util.reachThreshold(uuid)) {
            final Component message = MiniMessage.miniMessage().deserialize(CaeruleumCore.config.localeChangeIntervalLimitMessage());
            e.getPlayer().sendMessage(message);
            return;
        }

        String locale = e.locale().toString().toLowerCase();
        List<String> langPerms = PermUtil.getLangPerm(uuid);

        // Remove invalid perms
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

        // Certain locale code to certain locale code redirect
        if (!CaeruleumCore.config.localeRedirectList().isEmpty()) {
            for (String raw : CaeruleumCore.config.localeRedirectList()) {
                String[] split = raw.split(":");

                if (split.length == 2 && locale.equalsIgnoreCase(split[0])) {
                    locale = split[1];
                    break;
                }
            }
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
