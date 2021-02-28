package com.auster.thatcrapluxiandfischwantedonourserver.listeners;

import com.auster.thatcrapluxiandfischwantedonourserver.ThatCrapLuxiandFischWantedOnOurServer;
import com.auster.thatcrapluxiandfischwantedonourserver.utils.PlayerDataUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!PlayerDataUtils.exists(event.getPlayer().getUniqueId())) {
        PlayerDataUtils.register(event.getPlayer().getUniqueId());
        }
        if (!event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getModifiers().isEmpty()) {
            event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getModifiers().forEach(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH)::removeModifier);
        }
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        NecronArmorListener.players.remove(event.getPlayer().getUniqueId());
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onSpawn(EntitySpawnEvent entitySpawnEvent) {
        if (entitySpawnEvent.getEntity().getEntitySpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER)) {
            if (entitySpawnEvent.getLocation().getWorld().getName().equals("world")) {
                entitySpawnEvent.setCancelled(true);
            }

        }
    }

}
