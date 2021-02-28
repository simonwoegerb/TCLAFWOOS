package com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.enchants;

import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.Utils;
import com.auster.thatcrapluxiandfischwantedonourserver.utils.ReflectionUtils;
import com.auster.thatcrapluxiandfischwantedonourserver.utils.RunOnStartUp;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CustomEnchants implements RunOnStartUp {
    public static final String CUSTOMENCHANTS = "custom-enchantments";
    public static Map<Class<? extends MyEnchantment>, MyEnchantment> enchantments = new HashMap<>();

    public static boolean hasCustomEnchantments(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        if (itemStack.getType().name().toLowerCase().contains("air")) {

            return false;
        }

            NBTItem nbtItem = new NBTItem(itemStack);
        return nbtItem.hasKey(CUSTOMENCHANTS);
    }

    public static List<MyEnchantment> getEnchantments(ItemStack itemStack) {
        if (hasCustomEnchantments(itemStack)) {
            NBTItem item = new NBTItem(itemStack);
            List<MyEnchantment> list = new ArrayList<>();
            for (String s1 : item.getCompound(CUSTOMENCHANTS).getKeys()) {
                list.add(getEnchantment(s1));
            }
            Bukkit.getLogger().severe(list.isEmpty() + "");
            list.forEach(s -> Bukkit.getLogger().severe(s.getName()));
            return list;
        }
        return new ArrayList<>();
    }
    public static MyEnchantment getEnchantment(String name) {
        for (MyEnchantment entry : enchantments.values()) {
            if (entry.getName().equalsIgnoreCase(name)) return entry;
        }
        return null;
    }

    public static int getLevel(Class<? extends MyEnchantment> enchantment, ItemStack itemStack) {
        if (hasCustomEnchantments(itemStack)) {
            for (MyEnchantment s : getEnchantments(itemStack)) {
                if (s.getClass().equals(enchantment)) {
                    NBTItem item = new NBTItem(itemStack);
                    return item.getCompound(CUSTOMENCHANTS).getInteger(CustomEnchants.enchantments.get(enchantment).getName());

                }
            }
        }
        return 0;
    }

    public static MyEnchantment getEnchantment(Class<? extends MyEnchantment> e) {
        return enchantments.get(e);
    }

    public static void addEnchantment(MyEnchantment enchantment, int level, ItemStack itemStack) {
        if (itemStack == null) return;
        NBTItem item = new NBTItem(itemStack, true);
        if (!item.hasKey(CUSTOMENCHANTS)) {
            item.addCompound(CUSTOMENCHANTS);
        }
        var enchants = item.getCompound(CUSTOMENCHANTS);

        Bukkit.getLogger().severe(String.valueOf(enchantment.getName()));
        enchants.setInteger(enchantment.getName().trim(), level);

        Utils.applyLore(itemStack);
    }

    public static void addEnchantment(Class<? extends MyEnchantment> enchantment, int level, ItemStack itemStack) {
        addEnchantment(CustomEnchants.getEnchantment(enchantment), level, itemStack);
    }

    public static boolean hasEnchantment(MyEnchantment myEnchantment, ItemStack itemStack) {
        if (hasCustomEnchantments(itemStack)) {
            return getEnchantments(itemStack).contains(myEnchantment);
        }
        return false;
    }

    public static boolean hasEnchantment(Class<? extends MyEnchantment> enchantment, ItemStack itemStack) {
        return hasEnchantment(CustomEnchants.getEnchantment(enchantment), itemStack);
    }

    @Override
    public void onStartup(Plugin plugin) {
        var x = ReflectionUtils.reflections.getSubTypesOf(MyEnchantment.class);
        for (Class<? extends MyEnchantment> s : x) {
            try {
                enchantments.put(s, (MyEnchantment) s.getConstructors()[0].newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
