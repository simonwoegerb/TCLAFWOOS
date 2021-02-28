package com.auster.thatcrapluxiandfischwantedonourserver;

import com.auster.thatcrapluxiandfischwantedonourserver.config.ConfigHandler;
import com.auster.thatcrapluxiandfischwantedonourserver.config.Configs;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.enchants.CustomEnchants;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.enchants.Telekinesis;
import com.auster.thatcrapluxiandfischwantedonourserver.utils.PlayerDataUtils;
import com.auster.thatcrapluxiandfischwantedonourserver.utils.PriorityRunOnStartUp;
import com.auster.thatcrapluxiandfischwantedonourserver.utils.RunOnStartUp;
import com.auster.thatcrapluxiandfischwantedonourserver.utils.StartupRunner;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.slf4j.helpers.NOPLogger;

import java.lang.reflect.Field;

public final class ThatCrapLuxiandFischWantedOnOurServer extends JavaPlugin {
    private static ThatCrapLuxiandFischWantedOnOurServer instance;
    public ConfigHandler handler;

    public static ThatCrapLuxiandFischWantedOnOurServer getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        try {
            Field field = Reflections.class.getDeclaredField("log");
            field.setAccessible(true);
            field.set(null, NOPLogger.NOP_LOGGER);


        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        instance = this;
        handler = new ConfigHandler();
        handler.registerConfig(Configs.PLAYERDATA, this);
        StartupRunner.run(this, PriorityRunOnStartUp.class);
        StartupRunner.run(this, RunOnStartUp.class);
        getLogger().severe(CustomEnchants.getEnchantment(Telekinesis.class).getName());
        getLogger().severe("Loaded TCLaFWOOS v1");


    }
}
