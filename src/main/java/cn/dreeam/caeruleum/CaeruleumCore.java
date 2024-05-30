package cn.dreeam.caeruleum;

import cn.dreeam.caeruleum.config.Config;
import cn.dreeam.caeruleum.config.ConfigManager;
import cn.dreeam.caeruleum.listener.LocaleChange;
import cn.dreeam.caeruleum.utils.PermUtil;
import net.luckperms.api.LuckPerms;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CaeruleumCore extends JavaPlugin {

    private static CaeruleumCore instance;
    public static Logger LOGGER;

    public static LuckPerms luckPermsAPI;

    public ConfigManager<Config> configManager;
    public static Config config;
    private final PluginManager pluginManager = getServer().getPluginManager();

    @Override
    public void onEnable() {
        instance = this;
        LOGGER = LogManager.getLogger(instance.getName());

        loadConfig();
        registerEvents();
        initHooks();
        initTasks();
        new Metrics(instance, 22069);

        LOGGER.info("CaeruleumCore {} enabled. By Dreeam.", instance.getDescription().getVersion());
    }

    @Override
    public void onDisable() {
        LOGGER.info("CaeruleumCore {} disabled. By Dreeam", instance.getDescription().getVersion());
    }

    private void registerEvents() {
        pluginManager.registerEvents(new LocaleChange(), this);
    }

    private void loadConfig() {
        configManager = ConfigManager.create(instance.getDataFolder().toPath(), "config.yml", Config.class);
        configManager.reloadConfig();
        config = configManager.getConfigData();
    }

    private void initHooks() {
        // Hook LuckPerms
        RegisteredServiceProvider<LuckPerms> luckPermsProvider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (luckPermsProvider != null) {
            luckPermsAPI = luckPermsProvider.getProvider();
        } else {
            LOGGER.fatal("You need installing LuckPerms to get CaeruleumCore work!");
            getServer().shutdown();
        }
    }

    private void initTasks() {
        // Clear old perms task
        if (!config.oldLangPermPrefixList().isEmpty()) {
            Thread.startVirtualThread(
                    () -> Arrays.stream(Bukkit.getServer().getOfflinePlayers())
                    .filter(OfflinePlayer::hasPlayedBefore)
                    .collect(Collectors.toSet())
                    .forEach(
                            p -> config.oldLangPermPrefixList()
                                    .forEach(
                                    oldLangPermPrefix -> PermUtil.clearLangPerm(p.getUniqueId(), oldLangPermPrefix)
                            )
                    )
            );
        }
    }

    public static CaeruleumCore getInstance() {
        return instance;
    }
    public static LuckPerms getLuckPermsAPI() {
        return luckPermsAPI;
    }
}
