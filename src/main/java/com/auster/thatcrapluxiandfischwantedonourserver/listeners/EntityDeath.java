package com.auster.thatcrapluxiandfischwantedonourserver.listeners;

import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items.CustomItems;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.Utils;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.enchants.CustomEnchants;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.enchants.Telekinesis;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items.ZeStick;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class EntityDeath implements Listener {
    @EventHandler
    public void randomDrop(EntityDeathEvent event) {
        if (event.getEntity().getLastDamageCause() == null) return;
        if (event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK || event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK) {

            var customdrops = new ArrayList<ItemStack>();
            if (event.getEntity().getType().equals(EntityType.ENDERMAN)) {
                if (Utils.random(0, 200) == 1) {
                    customdrops.add(CustomItems.createItem(ZeStick.class, 1));
                    if (event.getEntity().isDead() && event.getEntity().getKiller() != null) {
                        event.getEntity().getKiller().sendTitle(ChatColor.DARK_RED + "Stick Dropped", "", 10, 20, 20);


                    }
                }

                event.getDrops().addAll(customdrops);
            }


        }
    }
    @EventHandler
    public void telekinesis(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null) return;

        if (CustomEnchants.getEnchantments(event.getEntity().getKiller().getInventory().getItemInMainHand()).contains(CustomEnchants.getEnchantment(Telekinesis.class))) {
            event.getEntity().getKiller().giveExp(event.getDroppedExp());
            event.setDroppedExp(0);
            event.getDrops().forEach(event.getEntity().getKiller().getInventory()::addItem);
            event.getDrops().clear();
        }


    }
    }




