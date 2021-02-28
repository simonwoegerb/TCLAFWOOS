package com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items;

import com.auster.thatcrapluxiandfischwantedonourserver.utils.PlayerDataUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.UUID;

public class ShadowFury extends MyItem {
    public static final int RANGE = 60;

    public ShadowFury() {
        name = "Shadow Fury";
        itemStackName = ChatColor.RED + "Shadow Fury";
        unbreakable = true;
        enchanted = false;
        material = Material.NETHERITE_SWORD;
        modelid = 1222333;

    }

    @Override
    public void onItemStackCreate(ItemStack var1) {
        var x = var1.getItemMeta();
        var1.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
        x.addAttributeModifier( Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", 10d, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        x.addAttributeModifier( Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.2d, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        x.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movement_speed", 0.05d, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        var1.setItemMeta(x);
    }

    @Override
    public ShapedRecipe setRecipe(ShapedRecipe recipe) {
        recipe.shape(" SW"," NS", "K  ");

        recipe.setIngredient('S', CustomItems.createItem(SoulOfTheShadows.class, 1));
        recipe.setIngredient('K', CustomItems.createItem(ZeMegaStick.class ,1));
        recipe.setIngredient('W', Material.WITHER_SKELETON_SKULL);
        recipe.setIngredient('N', Material.NETHERITE_SWORD);
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
        var entityCollection = var1.getLocation().getNearbyLivingEntities(PlayerDataUtils.getShadowFuryReach(var1.getUniqueId()));
        var x = new ArrayList<LivingEntity>();
        entityCollection.forEach(s -> {
            if (s.getType().equals(EntityType.PLAYER)) return;
            if (s.getType().equals(EntityType.BAT)) return;
            if (s.isDead()) return;
            x.add(s);
        });

        double smallest = RANGE;
        Entity entity = null;
        for (var z :
                x) {
            var distance = Math.sqrt(z.getLocation().distanceSquared(var1.getLocation()));
            if (distance < smallest) {
                smallest = distance;
                entity = z;
            }

        }
        if (entity != null) {
            var damageeLoc = entity.getLocation();
            double newX;
            double newZ;
            float nang = damageeLoc.getYaw() + 90;

            if (nang < 0) nang += 360;
            newX = Math.cos(Math.toRadians(nang));
            newZ = Math.sin(Math.toRadians(nang));
            Location newDamagerLoc = new Location(damageeLoc.getWorld(), damageeLoc.getX() - newX, damageeLoc.getY(), damageeLoc.getZ() - newZ, damageeLoc.getYaw(), damageeLoc.getPitch());

            var1.teleport(newDamagerLoc, PlayerTeleportEvent.TeleportCause.SPECTATE);


        }



    }
    @Override
    public void shiftRightClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {
        shiftRightClickAirAction(var1, var4);
    }

    @Override
    public void hitEntityAction(Player var1, EntityDamageByEntityEvent var2, Entity var3, ItemStack var4) {

    }

    @Override
    public void breakBlockAction(Player var1, BlockBreakEvent var2, Block var3, ItemStack var4) {

    }
}
