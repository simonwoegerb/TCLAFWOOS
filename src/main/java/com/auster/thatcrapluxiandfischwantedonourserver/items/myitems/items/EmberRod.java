package com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items;

import com.auster.thatcrapluxiandfischwantedonourserver.ThatCrapLuxiandFischWantedOnOurServer;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class EmberRod extends MyItem {
    public EmberRod() {
        material = Material.BLAZE_ROD;
        name = "Ember Rod";
        itemStackName = ChatColor.RED + "Ember Rod";
        modelid = 1234567;

    }

    @Override
    public void onItemStackCreate(ItemStack var1) {

    }

    @Override
    public ShapedRecipe setRecipe(ShapedRecipe recipe) {
        return null;
    }

    @Override
    public void leftClickAirAction(Player var1, ItemStack var2) {
    }

    public void onItemUse(Player var1, ItemStack var2) {
        NBTItem ni = new NBTItem(var2, true);
        if (ni.getBoolean("cooldown")) return;
        boolean success = false;
        if (var1.getGameMode().equals(GameMode.CREATIVE)) {
            var fb = var1.getWorld().spawn(var1.getEyeLocation(), Fireball.class);
            fb.setGlowing(false);
            fb.setBounce(true);
            fb.setYield(5f);
            success = true;
        } else if (var1.getInventory().contains(Material.MAGMA_CREAM)) {
            for (var item :
                    var1.getInventory()) {
                if (item.getType().equals(Material.MAGMA_CREAM)) {
                    item.setAmount(item.getAmount() - 1);
                    var fb = var1.getWorld().spawn(var1.getEyeLocation(), Fireball.class);
                    fb.setGlowing(false);
                    fb.setBounce(true);
                    fb.setYield(5f);
                    success = true;
                    break;
                }

            }

        }
        if (success) {
            ni.setBoolean("cooldown", true);
            Bukkit.getScheduler().scheduleSyncDelayedTask(ThatCrapLuxiandFischWantedOnOurServer.getInstance(), () -> {
                for (ItemStack itemsta : var1.getInventory().getContents()) {
                    if (CustomItems.isCustomItem(itemsta) && CustomItems.isType(itemsta, EmberRod.class)) {

                        NBTItem nbtItem = new NBTItem(itemsta, true);
                        if (nbtItem.getBoolean("cooldown")) {
                            nbtItem.setBoolean("cooldown", false);
                            break;
                        }
                    }

                }
            }, 20);
        }


    }

    @Override
    public void leftClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {
    }

    @Override
    public void rightClickAirAction(Player var1, ItemStack var2) {
        onItemUse(var1, var2);

    }

    @Override
    public void rightClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {
        onItemUse(var1, var4);

    }

    @Override
    public void shiftLeftClickAirAction(Player var1, ItemStack var2) {

    }

    @Override
    public void shiftLeftClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {

    }

    @Override
    public void shiftRightClickAirAction(Player var1, ItemStack var2) {

    }

    @Override
    public void shiftRightClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {

    }

    @Override
    public void hitEntityAction(Player var1, EntityDamageByEntityEvent var2, Entity var3, ItemStack var4) {

    }

    @Override
    public void breakBlockAction(Player var1, BlockBreakEvent var2, Block var3, ItemStack var4) {

    }
}
