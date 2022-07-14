package live.auster.staticitems.items.items;

import de.tr7zw.changeme.nbtapi.NBTItem;
import live.auster.staticitems.StaticItems;
import live.auster.staticitems.items.MyItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class AspectOfTheHoe extends MyItem {
    public final int COOLDOWN = 20;

    public AspectOfTheHoe() {
        itemStackName = Component.text("Aspect of the Hoe").color(NamedTextColor.RED);
        material = Material.NETHERITE_SWORD;
        canBreakBlocks = false;
        unbreakable = true;
    }

    public void onItemUse(Player var1) {
        NBTItem item = new NBTItem(var1.getInventory().getItemInMainHand(), true);
        int durability = item.getInteger("durability");
        boolean cooldown = item.getBoolean("cooldown");
        if (durability > 0 && !cooldown) {
            durability -= 1;
            item.setInteger("durability", durability);
            item.setBoolean("cooldown", true);
            Bukkit.getScheduler().scheduleSyncDelayedTask(StaticItems.getInstance(), () -> {
                for (ItemStack itemsta : var1.getInventory().getContents()) {
                    if (StaticItems.getInstance().getItemManager().isStaticItem(itemsta) && StaticItems.getInstance().getItemManager().isType(itemsta, AspectOfTheHoe.class)) {

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
            for (int i = 0; i < 20; i++) {
                var blocktype = loc.getBlock().getType();
                if (blocktype.isCollidable() && !blocktype.isSolid()) break;
                loc.add(var1.getEyeLocation().getDirection().
                        multiply(1d + 1 / 20));

            }

            var1.teleport(loc);
            var1.playSound(var1.getEyeLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.4f, 0.3f);


        }
    }

    @Override
    public void onItemStackCreate(ItemStack var1) {
        NBTItem item = new NBTItem(var1, true);
        item.setInteger("durability", 100);

    }

    @Override
    public ShapedRecipe setRecipe(ShapedRecipe recipe) {
        return null;
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
        onItemUse(var1);

    }

    @Override
    public void shiftRightClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4) {
        onItemUse(var1);

    }

    @Override
    public void hitEntityAction(Player var1, EntityDamageByEntityEvent var2, Entity var3, ItemStack var4) {

    }

    @Override
    public void breakBlockAction(Player var1, BlockBreakEvent var2, Block var3, ItemStack var4) {

    }

    @Override
    public void interactEntity(Player var1, PlayerInteractAtEntityEvent var2, Entity var3, ItemStack var4) {

    }
}