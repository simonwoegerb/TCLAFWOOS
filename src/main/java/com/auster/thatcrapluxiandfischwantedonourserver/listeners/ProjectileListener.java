package com.auster.thatcrapluxiandfischwantedonourserver.listeners;

import com.auster.thatcrapluxiandfischwantedonourserver.ThatCrapLuxiandFischWantedOnOurServer;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items.CustomItems;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items.ExplosiveArrow;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items.ThorSnowball;
import com.auster.thatcrapluxiandfischwantedonourserver.utils.PlayerDataUtils;
import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;

public class ProjectileListener implements Listener {

    @EventHandler
    public void onArrowLand(ProjectileHitEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            Player p = (Player) event.getEntity().getShooter();
            if (p.hasPermission("TCL.spectral_tnt")) {
                if (ExplosiveArrow.arrows.contains(event.getEntity().getUniqueId())) {
                    ExplosiveArrow.arrows.remove(event.getEntity().getUniqueId());

                    World w = event.getEntity().getWorld();
                    w.createExplosion(event.getEntity().getLocation(), PlayerDataUtils.getTNTExplosionForce(p.getUniqueId()));

                    event.getEntity().remove();
                }
            }

        }

    }

    @EventHandler
    public void onShoot(EntityShootBowEvent event) {
        if (event.getConsumable() == null) return;

        if (CustomItems.isType(event.getConsumable(), ExplosiveArrow.class)) {
            ExplosiveArrow.arrows.add(event.getProjectile().getUniqueId());
        }



    }
    @EventHandler
    public void onThrow(PlayerLaunchProjectileEvent event) {
        if (CustomItems.isType(event.getItemStack(), ThorSnowball.class)) {
            ThorSnowball.snowballs.add(event.getProjectile().getUniqueId());
        }
    }

    @EventHandler
    public void onSnowBallLand(ProjectileHitEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            Player p = (Player) event.getEntity().getShooter();
            if (p.hasPermission("TCL.snowball")) {
                if (ThorSnowball.snowballs.contains(event.getEntity().getUniqueId())) {
                    ThorSnowball.snowballs.remove(event.getEntity().getUniqueId());

                    var list = event.getEntity().getLocation().getNearbyLivingEntities(3d);
                    list.forEach(s -> s.setVelocity(p.getVelocity().add(new Vector(0, 20, 0))));
                    event.getEntity().getWorld().strikeLightning(event.getEntity().getLocation());

                }
            }

        }
        if (event.getEntity().getType().equals(EntityType.SNOWBALL)) {
            if (event.getEntity().getShooter() instanceof Player) {
                Player p = (Player) event.getEntity().getShooter();
                if (p.hasPermission("TCL.snowball_strike")) {

                }
            }

        }
    }
}

