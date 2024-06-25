package cn.dreeam.caeruleum.config;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfHeader;
import space.arim.dazzleconf.annote.ConfKey;
import space.arim.dazzleconf.sorter.AnnotationBasedSorter;

import java.util.List;

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
    List<String> oldLangPermPrefixList();

    @ConfKey("i18nPerm.main-locale")
    @ConfComments({
            "The main server language or the language that most of your server config use,",
            "CaeruleumCore will not assign lang perm for players who use this locale.",
            "The drawback is, player using locales instead of main locale list will see contents for their locale and the original content.",
            "Leave here blank if every i18n content that corresponds to perms."
    })
    @ConfDefault.DefaultStrings({
            "zh_cn",
            "zh_hk",
            "zh_tw",
            "lzh"
    })
    @AnnotationBasedSorter.Order(3)
    List<String> mainLocale();

    @ConfKey("i18nPerm.locale-change-interval-limit")
    @ConfComments({
            "The maximum interval for locale change, to prevent using packet exploit to lag server, locale change between this interval will be discard.",
            "(unit: ms)"
    })
    @ConfDefault.DefaultInteger(1000)
    @AnnotationBasedSorter.Order(4)
    int localeChangeIntervalLimit();

    @ConfKey("i18nPerm.locale-change-interval-limit-message")
    @ConfComments({
            "The message sent to player when reached the interval limit."
    })
    @ConfDefault.DefaultString("<red>You can not change locale so fast!")
    @AnnotationBasedSorter.Order(5)
    String localeChangeIntervalLimitMessage();

    @ConfKey("i18nPerm.locale-list.fallback-locale")
    @ConfComments({
            "The fallback locale, if any unknown or unsupported locale found when player join/change locale,",
            "CaeruleumCore will assign lang permission based on the fallback locale here."
    })
    @ConfDefault.DefaultString("en_us")
    @AnnotationBasedSorter.Order(6)
    String localeBlackWhiteListFallback();

    @ConfKey("i18nPerm.locale-list.use-whitelist")
    @ConfComments({
            "Set to true to use whitelist mode,",
            "Set to false to use blacklist mode."
    })
    @ConfDefault.DefaultBoolean(true)
    @AnnotationBasedSorter.Order(7)
    boolean localeBlackWhiteListUseWhitelist();

    @ConfKey("i18nPerm.locale-list.locales")
    @ConfComments({
            "As whitelist, CaeruleumCore will assign permission for players who use locales below, any other locale will fallback to fallback-locale.",
            "As blacklist, CaeruleumCore will fallback to fallback-locale for any locales in the list if players have."
    })
    @ConfDefault.DefaultStrings({
            "zh_cn",
            "zh_hk",
            "zh_tw",
            "lzh",
            "en_us",
            "en_au",
            "en_ca",
            "en_gb",
            "en_nz",
            "en_pt",
            "en_ud",
            "enp",
            "enws",
            "en_za",
            "de_de",
            "de_at",
            "de_ch",
            "swg",
            "fra_de",
            "ksh",
            "nds_de",
            "sxu",
            "fr_fr",
            "fr_ca",
            "ja_jp",
            "ko_kr",
            "ms_my",
            "zlm_arab",
            "ru_ru",
            "ba_ru",
            "rpr",
            "sah_sah",
            "tt_ru",
            "th_th"
    })
    @AnnotationBasedSorter.Order(8)
    List<String> localeBlackWhiteList();

    @ConfKey("i18nPerm.locale-code-redirect-list")
    @ConfComments({
            "Redirect locale code to certain locale code as you want",
            "Minecraft locale list: https://minecraft.wiki/w/Language",
            "For example, if you want to show Russian messages to player using language Tatar(tt_ru),",
            "and show Chinese Simplified messages to player using language Classical Chinese(lzh), then:",
            "locale-code-redirect-list:",
            "- 'tt_ru:ru_ru'",
            "- 'lzh:zh_cn'"
    })
    @ConfDefault.DefaultStrings({
            "lzh:zh_cn",
            "zh_HK:zh_cn",
            "zh_TW:zh_cn",
            "de_at:de_de",
            "de_ch:de_de",
            "swg:de_de",
            "fra_de:de_de",
            "ksh:de_de",
            "nds_de:de_de",
            "sxu:de_de",
            "en_au:en_us",
            "en_ca:en_us",
            "en_gb:en_us",
            "en_nz:en_us",
            "en_pt:en_us",
            "en_ud:en_us",
            "enp:en_us",
            "enws:en_us",
            "en_za:en_us",
            "fr_ca:fr_fr",
            "zlm_arab:ms_my",
            "ba_ru:ru_ru",
            "rpr:ru_ru",
            "sah_sah:ru_ru",
            "tt_ru:ru_ru"
    })
    @AnnotationBasedSorter.Order(9)
    List<String> localeRedirectList();
}

