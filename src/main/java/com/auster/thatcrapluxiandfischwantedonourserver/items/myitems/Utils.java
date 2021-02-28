package com.auster.thatcrapluxiandfischwantedonourserver.items.myitems;

import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items.CustomItems;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.enchants.CustomEnchants;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items.MyItem;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.BlockIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Utils {
    public static final Set<Material> TRANSPARENT = Set.of(Material.AIR, Material.CAVE_AIR, Material.VOID_AIR);


    public static Logger findLogger(Class<?> aClass) {
        try {

            Class.forName("org.slf4j.impl.StaticLoggerBinder");
            return LoggerFactory.getLogger(aClass);
        } catch (Throwable e) {
            return null;
        }
    }


    public static ItemStack applyLore(ItemStack result) {
        if (result == null) return null;
        List<String> enchants = new ArrayList<>();
        ItemMeta i = result.getItemMeta();
        if (!i.hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
            if (CustomEnchants.hasCustomEnchantments(result)) {
                Bukkit.getLogger().severe("HAS ENCHANTS");
                for (var x :
                        CustomEnchants.getEnchantments(result)) {

                    Bukkit.getLogger().severe("Name " + x.getName());
                    enchants.add(ChatColor.GRAY + x.getName() + " " + CustomEnchants.getLevel(x.getClass(), result));
                }


            }


            if (i.getLore() != null) enchants.addAll(i.getLore());
            i.setLore(enchants);
            result.setItemMeta(i);
        }
        return result;
        /*

         */
    }



    public static String convertIntToRomanNumeral(int i) {
        switch (i) {
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            case 9:
                return "IX";
            case 10:
                return "X";
            default:
                return "I";
        }
    }



    public static final Block getTargetBlock(Player player, int range) {
        BlockIterator iter = new BlockIterator(player, range);
        Block lastBlock = iter.next();
        while (iter.hasNext()) {
            lastBlock = iter.next();
            if (lastBlock.getType() == Material.AIR) {
                continue;
            }
            break;
        }
        return lastBlock;
    }

    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min) + 1 + min);
    }
}
