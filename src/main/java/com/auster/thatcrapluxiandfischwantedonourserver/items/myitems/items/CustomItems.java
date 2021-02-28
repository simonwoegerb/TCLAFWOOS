package com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items;

import com.auster.thatcrapluxiandfischwantedonourserver.ThatCrapLuxiandFischWantedOnOurServer;
import com.auster.thatcrapluxiandfischwantedonourserver.utils.PriorityRunOnStartUp;
import com.auster.thatcrapluxiandfischwantedonourserver.utils.ReflectionUtils;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CustomItems implements PriorityRunOnStartUp {
    //public static ItemStack KABOOM_ARROW = new ItemStack(Material.SPECTRAL_ARROW);
    public static Map<Class<? extends MyItem>, MyItem> classes = new HashMap<>();
    public static Map<String, Class<? extends MyItem>> names = new HashMap<>();

    public static boolean isCustomItem(ItemStack itemStack) {
        if (itemStack != null) {
            NBTItem item = new NBTItem(itemStack);
            return item.hasKey("CustomItem");
        }
        return false;

    }

    public static boolean isType(ItemStack itemStack, Class<? extends MyItem> clazz) {
        if (isCustomItem(itemStack)) {
            NBTItem item = new NBTItem(itemStack, true);
            if (item.hasKey("CustomItem")){
                return item.getString("CustomItem").equalsIgnoreCase(clazz.getCanonicalName());
            }
        }
        return false;

    }

    public static MyItem getCustomItem(ItemStack itemStack) {
        if (isCustomItem(itemStack)) {
            NBTItem nbtItem = new NBTItem(itemStack);
            for (Map.Entry<Class<? extends MyItem>, MyItem> entry : classes.entrySet()) {
                Class<? extends MyItem> x = entry.getKey();
                MyItem y = entry.getValue();
                if (x.getCanonicalName().equalsIgnoreCase(nbtItem.getString("CustomItem"))) {
                    return y;
                }
            }
        }
        throw new RuntimeException("This Item does not exist!");
    }
    public static ItemStack createItem(MyItem myItem, int amount) {
        ItemStack i = new ItemStack(myItem.material, amount);
        NBTItem item = new NBTItem(i, true);
        item.setString("CustomItem", myItem.getClass().getCanonicalName());
        if (myItem.modelid != 0) {
            item.setInteger("CustomModelData", myItem.modelid);
        }

        i = item.getItem();
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(myItem.itemStackName);
        meta.setLore(myItem.lore);
        if (myItem.enchanted) {
            meta.addEnchant(Enchantment.LUCK, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);


        }
        if (myItem.unbreakable) {
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            meta.setUnbreakable(true);

        }


        i.setLore(myItem.lore);
        i.setItemMeta(meta);
        myItem.onItemStackCreate(i);

        return i;
    }

    public static ItemStack createItem(Class<? extends MyItem> clazz, int amount) {
        return createItem(getItem(clazz), amount);
    }

    public static Map<String, Class<? extends MyItem>> getNames() {
        return names;
    }

    public static MyItem getItem(Class<? extends MyItem> clazz) {
        return classes.getOrDefault(clazz, null);
    }

    public static List<String> getClasses() {
        List<String> list = new ArrayList<>();
        classes.forEach((x, y) -> list.add(x.getSimpleName()));
        return list;
    }

    @Override
    public void onStartup(Plugin plugin) {
        ReflectionUtils.reflections.getSubTypesOf(MyItem.class).forEach(s -> {
            try {
                var x = (MyItem) s.getConstructors()[0].newInstance();
                register(x);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        classes.values().forEach( s -> {
            NamespacedKey namespacedKey = new NamespacedKey(ThatCrapLuxiandFischWantedOnOurServer.getInstance(), s.name.replaceAll(" ", "_").toLowerCase());
            ShapedRecipe x = new ShapedRecipe(namespacedKey, CustomItems.createItem(s, 1));

            x = s.setRecipe(x);
            try {
                Bukkit.addRecipe(x);


            } catch (NullPointerException ignored) {
            }

        });




    /*
        register(new ExplosiveArrow(new MyItemConfiguration(3).setModelId(1234456).setName("Explosive Arrow").setMaterial(Material.SPECTRAL_ARROW).setItemStackName(ChatColor.RED + "Explosive Arrow").setLore(Arrays.asList(ChatColor.RED + "An Arrow that Explodes", ChatColor.RED + "What the Hell were you expecting?")).setEnchanted(true).setCanBreakBlocks(true)));
        */
    }

    private void register(MyItem myItem) {
        classes.put(myItem.getClass(), myItem);
        names.put(myItem.getClass().getSimpleName(), myItem.getClass());
    }


}
