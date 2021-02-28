package com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items;

import com.auster.thatcrapluxiandfischwantedonourserver.ThatCrapLuxiandFischWantedOnOurServer;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.Utils;
import com.auster.thatcrapluxiandfischwantedonourserver.utils.PlayerDataUtils;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Arrays;
import java.util.List;

public  class AspectOfTheHoe extends MyItem {
    public static final int MAX_DURABILITY = 500;
    public static final int RANGE = 10;
    public static final int COOLDOWN = 20;
    public static List<Material> TRANSPARENT;

    public AspectOfTheHoe() {
        TRANSPARENT = Arrays.asList(Material.SUGAR_CANE, Material.AIR, Material.CAVE_AIR, Material.VOID_AIR, Material.TALL_GRASS, Material.GRASS, Material.SEAGRASS, Material.WATER, Material.LAVA);

        name = "Aspect of the Hoe";
        modelid = 1223344;
        material = Material.WOODEN_HOE;
        itemStackName = ChatColor.RED + "Aspect of the Hoe";
        ItemStack it;
        lore = Arrays.asList("§6Durability: §4UNUSED");
        enchanted = true;
        unbreakable = true;
    }

    @Override
    public void onItemStackCreate(ItemStack var1) {
        NBTItem item = new NBTItem(var1, true);
        item.setInteger("durability", MAX_DURABILITY);
        item.setBoolean("cooldown", false);

    }

    @Override
    public ShapedRecipe setRecipe(ShapedRecipe recipe) {

        recipe.shape("GCG", "ESE", "ESE");
        recipe.setIngredient('G', Material.GHAST_TEAR);
        recipe.setIngredient('C', Material.END_CRYSTAL);
        recipe.setIngredient('S', CustomItems.createItem(ZeStick.class, 1));
        recipe.setIngredient('E', Material.ENDER_EYE);

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
        onItemUse(var1);

    }

    @Override
    public void rightClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {
        onItemUse(var1);
    }

    private void onItemUse(Player var1) {
        NBTItem item = new NBTItem(var1.getInventory().getItemInMainHand(), true);
        int durability = item.getInteger("durability");
        boolean cooldown = item.getBoolean("cooldown");
        if (durability > 0 && !cooldown) {
            durability -= 1;
            item.setInteger("durability", durability);
            item.setBoolean("cooldown", true);
            Bukkit.getScheduler().scheduleSyncDelayedTask(ThatCrapLuxiandFischWantedOnOurServer.getInstance(), () -> {
                for (ItemStack itemsta : var1.getInventory().getContents()) {
                    if (CustomItems.isCustomItem(itemsta) && CustomItems.isType(itemsta, AspectOfTheHoe.class)) {

                        NBTItem nbtItem = new NBTItem(itemsta, true);
                        if (nbtItem.getBoolean("cooldown")) {
                            nbtItem.setBoolean("cooldown", false);
                            break;
                        }
                    }

                }
            }, COOLDOWN);
            var itemStack = item.getItem();
            var meta = itemStack.getItemMeta();
            var lore = meta.getLore();
            lore.set(0, "§6Durability: " + durability);
            meta.setLore(lore);
            itemStack.setItemMeta(meta);
            var1.getInventory().setItemInMainHand(itemStack);
            var loc = var1.getLocation().clone();
            loc.setDirection(var1.getLocation().getDirection());
            for (int i = 0; i < PlayerDataUtils.getAOTH_Reach(var1.getUniqueId()); i++) {
                if (!TRANSPARENT.contains(loc.getBlock().getType())) break;
                loc.add(var1.getEyeLocation().getDirection().multiply((double) (1d + 1 / PlayerDataUtils.getAOTH_Reach(var1.getUniqueId()))));
            }

            var1.teleport(loc);
            var1.playSound(var1.getEyeLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.4f, 0.3f);


        }


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
