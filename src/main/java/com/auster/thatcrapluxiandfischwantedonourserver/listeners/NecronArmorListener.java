package com.auster.thatcrapluxiandfischwantedonourserver.listeners;

import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items.*;
import com.auster.thatcrapluxiandfischwantedonourserver.utils.RunOnStartUp;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.beans.Customizer;
import java.util.*;
import java.util.stream.Collectors;


public class NecronArmorListener implements RunOnStartUp, Listener {
    public static Map<UUID, AttributeModifier> players = new HashMap<>();
    @Override
    public void onStartup(Plugin plugin) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            Bukkit.getOnlinePlayers().forEach(s -> {

                boolean hasfull = true;
                if (CustomItems.isType(s.getInventory().getChestplate(), NecronChestPlate.class)) {
                    s.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3*20,1));
                } else hasfull = false;
                if (CustomItems.isType(s.getInventory().getHelmet(), NecronHelmet.class)) {
                    s.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3*20,1));

                } else hasfull = false;
                if (CustomItems.isType(s.getInventory().getBoots(), NecronBoots.class)) {
                    s.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3*20,1));

                } else hasfull =false;
                if (CustomItems.isType(s.getInventory().getLeggings(), NecronLeggings.class)) {
                    s.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3*20,1));

                } else hasfull = false;
                if (hasfull) {
                    s.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2*20,2));

                    if (players.containsKey(s.getUniqueId())) return;
                    var x = new AttributeModifier("generic.max_health", 20d, AttributeModifier.Operation.ADD_NUMBER);
                        s.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(x);
                        players.put(s.getUniqueId(), x);
                    } else {
                    if (players.containsKey(s.getUniqueId())) {
                        s.getAttribute(Attribute.GENERIC_MAX_HEALTH).getModifiers().forEach( s.getAttribute(Attribute.GENERIC_MAX_HEALTH)::removeModifier);
                        players.remove(s.getUniqueId());

                    }


                }


            });
        }, 60, 25);
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.getMessage().equalsIgnoreCase("RESET")) {
            if (event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH) == null) return;

            event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getModifiers().clear();

        }
    }
}
