package cn.dreeam.caeruleum.utils;

import cn.dreeam.caeruleum.CaeruleumCore;

public class Util {

    private static long lastTime = System.nanoTime();

    public static boolean reachThreshold() {
        long now = System.nanoTime();
        if ((now - lastTime) / 1_000_000 > CaeruleumCore.config.localeChangeIntervalLimit()) {
            lastTime = now;
            return true;
        }

        return false;
    }
}
