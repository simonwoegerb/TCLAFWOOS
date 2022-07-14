package live.auster.staticitems.listeners;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;

public final class ListenerHandler {

    public ListenerHandler(Plugin plugin) {
        try (ScanResult scanResult = new ClassGraph().acceptPackages("live.auster.staticitems.listeners")
                .enableClassInfo().scan()) {
            java.util.List<Class<Listener>> result = scanResult
                    .getClassesImplementing(Listener.class.getName())
                    .loadClasses(Listener.class);
            for (var listener : result) {
                registerListener(listener, plugin);
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + listener.getSimpleName());
            }
        }
    }

    public void registerListener(Class<? extends Listener> clazz, Plugin plugin) {
        try {
            var instance = clazz.getConstructor().newInstance();
            Bukkit.getPluginManager().registerEvents(instance, plugin);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException |
                 NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


}
