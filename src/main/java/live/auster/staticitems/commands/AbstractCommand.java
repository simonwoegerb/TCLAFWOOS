package live.auster.staticitems.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class AbstractCommand implements CommandExecutor, TabCompleter {
    public final String command, permission;

    public AbstractCommand(String command, String permission) {
        this.command = command;
        this.permission = permission;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = null;
        if (sender.hasPermission(permission)) {
            if (sender instanceof Player) {
                p = (Player) sender;
            }
            execute(sender, p, p != null, command, args);
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return tab(sender, command, args);

    }

    public abstract List<String> tab(CommandSender sender, Command command, String[] args);

    public abstract void execute(CommandSender sender, Player p, boolean isPlayer, Command command, String[] args);


}
