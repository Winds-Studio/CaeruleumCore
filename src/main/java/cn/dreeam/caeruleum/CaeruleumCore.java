package cn.dreeam.caeruleum;

import cn.dreeam.caeruleum.config.Config;
import cn.dreeam.caeruleum.config.ConfigManager;
import cn.dreeam.caeruleum.listener.LocaleChange;
import cn.dreeam.caeruleum.listener.PlayerJoin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CaeruleumCore extends JavaPlugin {

    private static CaeruleumCore instance;
    public static Logger LOGGER;

    public ConfigManager<Config> configManager;
    public static Config config;
    private final PluginManager pluginManager = getServer().getPluginManager();

    @Override
    public void onEnable() {
        instance = this;
        LOGGER = LogManager.getLogger(instance.getName());

        instance.loadConfig();
        instance.registerEvents();
        //new Metrics(instance, 16810);

        LOGGER.info("CaeruleumCore {} enabled. By Dreeam.", instance.getDescription().getVersion());
    }

    @Override
    public void onDisable() {
        LOGGER.info("CaeruleumCore {} disabled. By Dreeam", instance.getDescription().getVersion());
    }

    public void registerEvents() {
        pluginManager.registerEvents(new LocaleChange(), this);
        pluginManager.registerEvents(new PlayerJoin(), this);
    }

    public void loadConfig() {
        configManager = ConfigManager.create(instance.getDataFolder().toPath(), "config.yml", Config.class);
        configManager.reloadConfig();
        config = configManager.getConfigData();
    }

    public static CaeruleumCore getInstance() {
        return instance;
    }
}
