package cn.dreeam.caeruleum.utils;

import cn.dreeam.caeruleum.CaeruleumCore;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Util {

    private static final Map<UUID, Long> playerRateLimit = new ConcurrentHashMap<>();

    public static boolean reachThreshold(UUID uuid) {
        long now = System.nanoTime();

        Long lastTime = playerRateLimit.get(uuid);
        if (lastTime == null) {
            playerRateLimit.put(uuid, now);
            return false;
        }

        if ((now - lastTime) / 1_000_000 <= CaeruleumCore.config.localeChangeIntervalLimit()) {
            return true;
        } else {
            playerRateLimit.put(uuid, now);
            return false;
        }
    }

    public static void clearPlayerRateLimit(UUID uuid) {
        playerRateLimit.remove(uuid);
    }
}
