package com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items;

import com.auster.thatcrapluxiandfischwantedonourserver.ThatCrapLuxiandFischWantedOnOurServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;

public abstract class MyItem {
    public boolean enchanted = false;
    public boolean canBreakBlocks = false;
    public int modelid =  0;
    public String name = "UNFINISHED " + this.getClass().getSimpleName() ;
    public String itemStackName = ChatColor.DARK_RED + "UNFINISHED";
    public List<String> lore = null;
    public Material material = Material.DIRT;
    public boolean canBePlaced = false;
    public boolean unbreakable = false;
    public boolean stackable = false;


    public MyItem() {




    }



        /*this. configuration.id;
        this.name = configuration.name;
        this.material = configuration.material;
        this.itemStackName = configuration.itemStackName;
        this.enchanted = configuration.enchanted;
        this.model configuration.modelId;
        this.canBreakBlocks = configuration.canBreakBlocks;
        this.lore = configuration.lore == null? new ArrayList<>() : configuration.lore;*/



    /*private boolean enchanted;
    private int model 0;
    private int id;
    private String name;
    private String itemStackName;
    private List<String> lore;
    private Material material;
    private boolean canBreakBlocks;
    */


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


}
