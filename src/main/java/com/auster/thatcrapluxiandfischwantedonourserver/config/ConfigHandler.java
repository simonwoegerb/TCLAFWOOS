package com.auster.thatcrapluxiandfischwantedonourserver.config;


import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class ConfigHandler {
    //ArrayList<MyConfig> configs = new ArrayList<>();
    private final Map<String, MyConfig> configs;

    public ConfigHandler() {
        configs = new HashMap<>();
    }

    public void registerConfig(String name, Plugin plugin) {
        MyConfig config = new MyConfig(name, plugin);
        configs.put(config.getName(), config);
    }

    public Map<String, MyConfig> getConfigs() {
        return configs;
    }

    public MyConfig getConfig(String name) {

        return configs.getOrDefault(name, null);
    }
}