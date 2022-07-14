package live.auster.staticitems.items;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class ReflectionItemRegistry implements ItemRegistry {
    private final String packageName;

    public ReflectionItemRegistry(String packageName) {
        if (packageName.isEmpty())
            this.packageName = "live.auster.items";
        else
            this.packageName = packageName;
    }


    @Override
    public List<MyItem> getRegisteredItems() {
        try (ScanResult scanResult = new ClassGraph().acceptPackages(packageName)
                .enableClassInfo().scan()) {
            List<Class<MyItem>> result = scanResult
                    .getSubclasses(MyItem.class.getName())
                    .loadClasses(MyItem.class);
            var r = result.stream().map(s -> {
                try {
                    Bukkit.getLogger().log(Level.ALL, s.getName());

                    return s.getConstructor(null).newInstance(null);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());

            return r;
        }
    }
}

