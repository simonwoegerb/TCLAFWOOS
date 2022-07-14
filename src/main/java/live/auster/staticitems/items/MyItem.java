package live.auster.staticitems.items;

import com.google.common.collect.Lists;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;
import java.util.Locale;

public abstract class MyItem {
    public boolean enchanted = false;
    public boolean canBreakBlocks = false;
    public int model_id = 0;
    public String name = this.getClass().getSimpleName().toLowerCase(Locale.ROOT);
    public Component itemStackName = Component.text("UNFINISHED").color(TextColor.fromHexString("#FF0000"));
    public List<Component> lore = Lists.newArrayList(Component.text("Testing").color(TextColor.fromHexString("FFAABB")));
    public Material material = Material.DIRT;
    public boolean canBePlaced = false;
    public boolean unbreakable = false;
    public boolean stackable = false;
    public ItemRarity rarity = ItemRarity.UNOBTAINABLE;

    public MyItem() {
        PlayerInteractAtEntityEvent event;

    }



    public abstract void onItemStackCreate(ItemStack var1);

    public abstract ShapedRecipe setRecipe(ShapedRecipe recipe);

    public abstract void leftClickAirAction(Player var1, ItemStack var2);

    public abstract void leftClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4);

    public abstract void rightClickAirAction(Player var1, ItemStack var2);

    public abstract void rightClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4);

    public abstract void shiftLeftClickAirAction(Player var1, ItemStack var2);

    public abstract void shiftLeftClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4);

    public abstract void shiftRightClickAirAction(Player var1, ItemStack var2);

    public abstract void shiftRightClickBlockAction(Player var1, PlayerInteractEvent var2, Block var3, ItemStack var4);


    public abstract void hitEntityAction(Player var1, EntityDamageByEntityEvent var2, Entity var3, ItemStack var4);

    public abstract void breakBlockAction(Player var1, BlockBreakEvent var2, Block var3, ItemStack var4);

    public abstract void interactEntity(Player var1, PlayerInteractAtEntityEvent var2, Entity var3, ItemStack var4);

}
