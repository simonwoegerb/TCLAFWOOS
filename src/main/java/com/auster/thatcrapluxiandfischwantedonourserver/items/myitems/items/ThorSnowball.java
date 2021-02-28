package com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ThorSnowball extends MyItem {
    public static List<UUID> snowballs = new ArrayList<>();
    public ThorSnowball() {
        material = Material.SNOWBALL;
        name = "Thor Snowball";
        itemStackName = ChatColor.BLUE + "Thorstens Snowball";
        enchanted = true;
        modelid = 123;

    }

    @Override
    public void onItemStackCreate(ItemStack var1) {

    }

    @Override
    public ShapedRecipe setRecipe(ShapedRecipe recipe) {

        recipe.shape("FSF", "SBS", "FSF");
        recipe.setIngredient('F', Material.FEATHER);
        recipe.setIngredient('S', Material.PRISMARINE_CRYSTALS);
        recipe.setIngredient('B', Material.SNOWBALL);

        return recipe;
    }

    @Override
    public void leftClickAirAction(Player var1, ItemStack var2) {

    }

    @Override
    public void leftClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {

    }

    @Override
    public void rightClickAirAction(Player var1, ItemStack var2) {

    }

    @Override
    public void rightClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {

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
