package com.auster.thatcrapluxiandfischwantedonourserver.listeners;

import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.enchants.CustomEnchants;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.enchants.Telekinesis;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items.CustomItems;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items.MyItem;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerUseItem implements Listener {
    public PlayerUseItem() {

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (CustomItems.isCustomItem(event.getItem())) {
            MyItem myItem = CustomItems.getCustomItem(event.getItem());
            if (myItem == null) return;
            if (!event.getPlayer().isSneaking()) {
                switch (event.getAction()) {
                    case LEFT_CLICK_AIR:
                        myItem.leftClickAirAction(event.getPlayer(), event.getItem());
                        break;
                    case RIGHT_CLICK_BLOCK:
                        myItem.rightClickBlockAction(event.getPlayer(), event, event.getClickedBlock(), event.getItem());
                        break;
                    case RIGHT_CLICK_AIR:
                        myItem.rightClickAirAction(event.getPlayer(), event.getItem());
                        break;
                    case LEFT_CLICK_BLOCK:
                        myItem.leftClickBlockAction(event.getPlayer(), event, event.getClickedBlock(), event.getItem());
                        break;
                }
            } else {
                switch (event.getAction()) {
                    case LEFT_CLICK_AIR:
                        myItem.shiftLeftClickAirAction(event.getPlayer(), event.getItem());
                        break;
                    case RIGHT_CLICK_BLOCK:
                        myItem.shiftRightClickBlockAction(event.getPlayer(), event, event.getClickedBlock(), event.getItem());
                        break;
                    case RIGHT_CLICK_AIR:
                        myItem.shiftRightClickAirAction(event.getPlayer(), event.getItem());
                        break;
                    case LEFT_CLICK_BLOCK:
                        myItem.shiftLeftClickBlockAction(event.getPlayer(), event, event.getClickedBlock(), event.getItem());
                        break;
                }
            }

        }
    }

    @EventHandler
    public void placeBlock(BlockPlaceEvent event) {
        if (event.getItemInHand().getType().isAir()) return;
        if (CustomItems.isCustomItem(event.getItemInHand()) && !CustomItems.getCustomItem(event.getItemInHand()).canBePlaced) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getType().isAir()) return;
        if (CustomItems.isCustomItem(event.getPlayer().getInventory().getItemInMainHand())) {
            if (CustomEnchants.hasEnchantment(Telekinesis.class, event.getPlayer().getInventory().getItemInMainHand())) {
            event.setDropItems(false);
            event.getPlayer().giveExp(event.getExpToDrop());
            event.setExpToDrop(0);
            event.setDropItems(false);
            event.getBlock().breakNaturally(event.getPlayer().getInventory().getItemInMainHand());
            
            }
        }

        if (CustomItems.isCustomItem(event.getPlayer().getInventory().getItemInMainHand())) {
            if (!CustomItems.getCustomItem(event.getPlayer().getInventory().getItemInMainHand()).canBreakBlocks) {
                event.setCancelled(true);

            }
        }
    }
}
