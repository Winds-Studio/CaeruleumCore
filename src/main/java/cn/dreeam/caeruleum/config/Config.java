package cn.dreeam.caeruleum.config;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfHeader;
import space.arim.dazzleconf.annote.ConfKey;
import space.arim.dazzleconf.sorter.AnnotationBasedSorter;

@ConfHeader({
        "CaeruleumCore 1.0.0-SNAPSHOT",
        "Contact me on QQ:2682173972 or Discord: dreeam___",
        "For help with this plugin"
})
public interface Config {

    @ConfKey("i18nPerm.prefix")
    @ConfComments({
            "The Prefix of i18n lang permission"
    })
    @ConfDefault.DefaultString("winds.i18n.key.")
    @AnnotationBasedSorter.Order(1)
    String langPermKeyPrefix();

    @ConfKey("i18nPerm.old-perm-prefix-list")
    @ConfComments({
            "Add previous or old i18n permission prefix here",
            "When CaeruleumCore loaded, it will help you check and delete all old permissions if any player has."
    })
    @ConfDefault.DefaultStrings({
            "winds.i18n.oldkey.example."
    })
    @AnnotationBasedSorter.Order(2)
    String[] oldLangPermPrefixList();

    /*
    Need redesign here, since, if don't assign any lang permission to those content, it will show for all players.
    So players use other locale will see both content for main locale and the contents for their locale.
    Need to move to another force-group setting and use group structure, e.g. {"zh_HK", "zh_TW", "lzh"} -> "zh_CN"
     */
    @ConfKey("i18nPerm.main-locale")
    @ConfComments({
            "The main server language or the language that most of your server config use,",
            "CaeruleumCore will not assign lang perm for players who use this locale."
    })
    @ConfDefault.DefaultStrings({
            "zh_CN",
            "zh_HK",
            "zh_TW",
            "lzh"
    })
    @AnnotationBasedSorter.Order(3)
    String[] mainLocale();

    @ConfKey("i18nPerm.locale-list.fallback-locale")
    @ConfComments({
            "The fallback locale, if any unknown or unsupported locale found when player join/change locale,",
            "CaeruleumCore will assign lang permission based on the fallback locale here."
    })
    @ConfDefault.DefaultString("en_US")
    @AnnotationBasedSorter.Order(4)
    String localeBlackWhiteListFallback();

    @ConfKey("i18nPerm.locale-list.use-whitelist")
    @ConfComments({
            "Set to true to use whitelist mode,",
            "Set to false to use blacklist mode."
    })
    @ConfDefault.DefaultBoolean(true)
    @AnnotationBasedSorter.Order(5)
    boolean localeBlackWhiteListUseWhitelist();

    @ConfKey("i18nPerm.locale-list.locales")
    @ConfComments({
            "As whitelist, CaeruleumCore will assign permission for players who use locales below, any other locale will fallback to fallback-locale.",
            "As blacklist, CaeruleumCore will fallback to fallback-locale for any locales in the list if players have."
    })
    @ConfDefault.DefaultStrings({
            "zh_CN",
            "zh_HK",
            "zh_TW",
            "lzh",
            "en_US"
    })
    @AnnotationBasedSorter.Order(6)
    String[] localeBlackWhiteList();
}

