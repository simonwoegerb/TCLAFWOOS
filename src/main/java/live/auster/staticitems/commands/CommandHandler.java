package live.auster.staticitems.commands;

import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private final static Map<String, AbstractCommand> commands = new HashMap<>();

    public CommandHandler(Plugin plugin) {
        registerCommand(plugin, GiveItemCommand.class, "giveitem", "staticitems.giveitem");
    }

    public void registerCommand(Plugin plugin, Class<? extends AbstractCommand> clazz, String name, String permission) {
        try {

            AbstractCommand command = clazz.getConstructor(String.class, String.class).newInstance(name, permission);
            plugin.getServer().getPluginCommand(command.command).setExecutor(command);
            plugin.getServer().getPluginCommand(command.command).setTabCompleter(command);
            commands.put(name, command);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


}
