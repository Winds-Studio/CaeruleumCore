package cn.dreeam.caeruleum;

import cn.dreeam.caeruleum.config.Config;
import cn.dreeam.caeruleum.config.ConfigManager;
import cn.dreeam.caeruleum.listener.LocaleChange;
import net.luckperms.api.LuckPerms;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

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
        //new Metrics(instance, 16810);

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
        RegisteredServiceProvider<LuckPerms> luckPermsProvider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (luckPermsProvider != null) {
            luckPermsAPI = luckPermsProvider.getProvider();
        } else {
            LOGGER.fatal("You need installing LuckPerms to let CaeruleumCore work!");
            getServer().shutdown();
        }
    }

    public static CaeruleumCore getInstance() {
        return instance;
    }
    public static LuckPerms getLuckPermsAPI() {
        return luckPermsAPI;
    }
}
