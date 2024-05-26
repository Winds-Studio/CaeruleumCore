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
}

