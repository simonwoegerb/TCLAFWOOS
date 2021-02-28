package com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.recipe;

import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.Utils;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.enchants.CustomEnchants;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.enchants.Telekinesis;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items.AspectOfTheHoe;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items.CustomItems;
import com.auster.thatcrapluxiandfischwantedonourserver.utils.RunOnStartUp;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.plugin.Plugin;

public class AnvilRecipeHandler implements RunOnStartUp, Listener {
    @Override
    public void onStartup(Plugin plugin) {

    }

    @EventHandler
    public void repairAOTH(PrepareAnvilEvent event) {
        if (event.getInventory().getSecondItem() == null) return;
        if (event.getInventory().getFirstItem() == null) return;
        //TODO FIX
        if (CustomItems.isCustomItem(event.getInventory().getFirstItem()) && CustomItems.isType(event.getInventory().getFirstItem(), AspectOfTheHoe.class)&& event.getInventory().getSecondItem().getType().equals(Material.END_CRYSTAL) && event.getInventory().getSecondItem().getAmount() == 1) {
            NBTItem item = new NBTItem(event.getInventory().getFirstItem());
            item.setInteger("durability", 500);
            event.setResult(item.getItem());
            event.getInventory().setRepairCost(30);
        }
    }

    @EventHandler
    public void addTelekinesis(PrepareAnvilEvent event) {
        if (event.getInventory().getFirstItem() == null) return;
        if (event.getInventory().getSecondItem() == null) return;
        if (event.getInventory().getSecondItem().getType().equals(Material.NETHER_STAR)) {
            var x = event.getInventory().getFirstItem().clone();
            CustomEnchants.addEnchantment(Telekinesis.class, 1, x);
            event.setResult(x);
            event.getInventory().setRepairCost(30);


        }
    }
}
