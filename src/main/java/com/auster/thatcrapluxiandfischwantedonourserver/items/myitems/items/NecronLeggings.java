package com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.UUID;

public class NecronLeggings extends MyItem {
    public NecronLeggings() {
        material = Material.LEATHER_LEGGINGS;
        itemStackName = ChatColor.RED + "NECRON LEGGINGS";
        name = "Necron Leggings";
        unbreakable = true;
        enchanted = true;

    }

    @Override
    public void onItemStackCreate(ItemStack var1) {
        LeatherArmorMeta meta = (LeatherArmorMeta) var1.getItemMeta();
        meta.setColor(Color.fromRGB(183, 59, 39));
        Player p;

        meta.addAttributeModifier(Attribute.GENERIC_ARMOR,  new AttributeModifier(UUID.randomUUID(),"generic.armor", 20, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
        var1.setItemMeta(meta);
    }

    @Override
    public ShapedRecipe setRecipe(ShapedRecipe recipe) {
        recipe.shape("B B", "NCN", "BSB");
        recipe.setIngredient('B', Material.BLAZE_ROD);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('C', Material.NETHERITE_LEGGINGS);
        recipe.setIngredient('S', Material.NETHER_STAR);


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
