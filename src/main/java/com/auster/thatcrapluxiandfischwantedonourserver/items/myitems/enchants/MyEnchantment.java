package com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.enchants;

import org.bukkit.inventory.ItemStack;

public abstract class MyEnchantment {
    protected String name = "INVALID";
    protected int maxLevel = 1;

    public MyEnchantment() {


    }


    public String getName() {
        return name;
    }

    public int getMaxLevel() {
        return maxLevel;
    }


    public boolean canEnchantItem(ItemStack item) {

        return true;
    }
}
