package com.auster.thatcrapluxiandfischwantedonourserver.utils;


import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;

public class StartupRunner {
    public static void init(Plugin plugin) {
        run(plugin, PriorityRunOnStartUp.class);
        run(plugin, RunOnStartUp.class);

    }

    public static void run(Plugin plugin, Class<? extends BaseRunOnStartup> clazz) {
        var y = ReflectionUtils.reflections.getSubTypesOf(clazz);
        y.forEach(c -> {
            try {
                c.getConstructor().newInstance().onStartup(plugin);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
    }

}

