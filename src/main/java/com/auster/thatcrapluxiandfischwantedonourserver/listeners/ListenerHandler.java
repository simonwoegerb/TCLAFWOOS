package com.auster.thatcrapluxiandfischwantedonourserver.listeners;

import com.auster.thatcrapluxiandfischwantedonourserver.utils.RunOnStartUp;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.InvocationTargetException;

public final class ListenerHandler implements RunOnStartUp {
    private static final Reflections reflections = new Reflections(new ConfigurationBuilder().addScanners(new SubTypesScanner()).setUrls(ClasspathHelper.forPackage("com.auster.thatcrapluxiandfischwantedonourserver")));

    public ListenerHandler() {
    }

    public static void registerListener(Class<? extends Listener> clazz, Plugin plugin) {
        Bukkit.getLogger().severe(clazz.getSimpleName() + "!!!!!");
        try {
            var instance = clazz.getConstructor().newInstance((Object[]) null);
            Bukkit.getPluginManager().registerEvents(instance, plugin);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onStartup(Plugin plugin) {
        reflections.getSubTypesOf(Listener.class).forEach(s -> registerListener(s, plugin));

    }

}
