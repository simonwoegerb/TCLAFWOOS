package live.auster.staticitems.items;

import de.tr7zw.changeme.nbtapi.NBTItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemManager {
    public final Map<Class<? extends MyItem>, MyItem> items = new HashMap<>();

    public ItemManager(ItemRegistry itemRegistry) {
        addItemRegistry(itemRegistry);
    }

    public Map<Class<? extends MyItem>, MyItem> getItems() {
        return items;
    }

    public void addItemRegistry(ItemRegistry itemRegistry) {
        itemRegistry.getRegisteredItems().forEach(s -> items.put(s.getClass(), s));

    }

    public MyItem getItem(Class<? extends MyItem> item) {
        return items.get(item);
    }

    public MyItem getItem(ItemStack itemStack) {
        NBTItem item = new NBTItem(itemStack);
        String name = item.getString("type");
        for (var s : items.entrySet()) {
            if (s.getValue().getClass().getName().equals(name)) {
                return getItem(s.getKey());
            }
        }
        return null;
    }

    public boolean isStaticItem(ItemStack itemStack) {
        if (itemStack.getType().isAir()) return false;
        NBTItem item = new NBTItem(itemStack);
        return item.hasKey("type");
    }

    public ItemStack generateItemStack(Class<? extends MyItem> item, int size) {
        return generateItemStack(getItem(item), size);
    }

    public boolean isType(ItemStack itemStack, Class<? extends MyItem> clazz) {
        if (isStaticItem(itemStack)) {
            return getItem(itemStack).getClass().equals(clazz);
        }
        return false;
    }

    public ItemStack generateItemStack(MyItem item, int size) {
        if (!item.stackable) size = 1;
        ItemStack itemStack = new ItemStack(item.material, size);
        var s = itemStack.getItemMeta();
        s.displayName(item.itemStackName);
        List<Component> x = item.lore.stream().map(y -> y.decoration(TextDecoration.ITALIC, false).color(TextColor.fromHexString("#909090"))).collect(Collectors.toList());
        x.add(item.rarity.title);
        s.lore(x);
        s.setUnbreakable(item.unbreakable);
        itemStack.setItemMeta(s);
        NBTItem nbt = new NBTItem(itemStack, true);
        nbt.setString("type", item.getClass().getName());
        item.onItemStackCreate(itemStack);
        return itemStack;
    }
}
