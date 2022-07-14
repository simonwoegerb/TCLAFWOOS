package live.auster.staticitems;

import de.tr7zw.nbtinjector.NBTInjector;
import live.auster.staticitems.commands.CommandHandler;
import live.auster.staticitems.items.ItemManager;
import live.auster.staticitems.items.ItemRegistry;
import live.auster.staticitems.items.ReflectionItemRegistry;
import live.auster.staticitems.listeners.ListenerHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaticItems extends JavaPlugin {
    private static StaticItems instance;
    private ItemRegistry itemRegistry;
    private ItemManager itemManager;
    private CommandHandler commandHandler;
    private ListenerHandler listenerHandler;

    public static StaticItems getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        NBTInjector.inject();

    }

    @Override
    public void onEnable() {
        itemRegistry = new ReflectionItemRegistry("live.auster.staticitems.items");
        itemManager = new ItemManager(itemRegistry);
        commandHandler = new CommandHandler(this);
        listenerHandler = new ListenerHandler(this);

        instance = this;

    }

    public ItemManager getItemManager() {
        return itemManager;
    }
}
