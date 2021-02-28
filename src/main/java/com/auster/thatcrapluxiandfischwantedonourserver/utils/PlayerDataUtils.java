package com.auster.thatcrapluxiandfischwantedonourserver.utils;

import com.auster.thatcrapluxiandfischwantedonourserver.ThatCrapLuxiandFischWantedOnOurServer;
import com.auster.thatcrapluxiandfischwantedonourserver.config.Configs;
import com.auster.thatcrapluxiandfischwantedonourserver.config.MyConfig;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

import java.util.UUID;

public class PlayerDataUtils implements RunOnStartUp {
    public static final String TNTFORCE = "tnt_force";
    public static final String ASPECT_OF_THE_HOE_REACH = "aoth_reach";
    public static final String SHADOW_FURY_REACH = "shadowfury-reach";
    public static MyConfig data;



    public static boolean exists(UUID uuid) {
        return data.getConfigurationSection(uuid + "") != null;
    }

    public static float getTNTExplosionForce(UUID uuid) {
        if (exists(uuid)) {
            return data.getConfigurationSection(uuid + "").getInt(TNTFORCE);
        }
        return 1f;
    }

    public static void setTNTExplosionForce(UUID uuid, float power) {
        if (exists(uuid)) {
            data.getConfigurationSection(String.valueOf(uuid)).set(TNTFORCE, power + 0);
            data.save();
        }
    }

    public static int getAOTH_Reach(UUID uuid) {
        if (exists(uuid)) {
            return data.getConfigurationSection(String.valueOf(uuid)).getInt(ASPECT_OF_THE_HOE_REACH);
        }
        return 8;
    }
    public static int getShadowFuryReach(UUID uuid) {
        if (exists(uuid)) {
            return data.getConfigurationSection(String.valueOf(uuid)).getInt(SHADOW_FURY_REACH);
        }
        return 16;
    }

    public static void register(UUID uuid) {
        ConfigurationSection cs = data.createSection(uuid + "");
        cs.set(TNTFORCE, 1);
        cs.set(ASPECT_OF_THE_HOE_REACH, 8);
        cs.set(SHADOW_FURY_REACH, 16);
        data.save();
    }

    @Override
    public void onStartup(Plugin plugin) {
    PlayerDataUtils.data = ThatCrapLuxiandFischWantedOnOurServer.getInstance().handler.getConfig(Configs.PLAYERDATA);

    }
}
