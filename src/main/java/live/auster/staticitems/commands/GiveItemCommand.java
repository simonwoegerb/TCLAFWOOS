package live.auster.staticitems.commands;

import live.auster.staticitems.StaticItems;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class GiveItemCommand extends AbstractCommand {
    public GiveItemCommand(String command, String permission) {
        super(command, permission);
    }

    @Override
    public List<String> tab(CommandSender sender, Command command, String[] args) {
        if (args.length == 1) {
            return Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList());
        } else {
            return StaticItems.getInstance().getItemManager().getItems().values().stream().map(s -> s.name).collect(Collectors.toList());
        }
    }

    @Override
    public void execute(CommandSender sender, Player p, boolean isPlayer, Command command, String[] args) {
        if (args.length == 2) {
            var item = StaticItems.getInstance().getItemManager().getItems().values().stream().filter(s -> s.name.toLowerCase(Locale.ROOT).equals(args[1])).findFirst();
            var x = StaticItems.getInstance().getItemManager().generateItemStack(item.orElse(null), 1);
            if (isPlayer) {
                p.getInventory().addItem(x);
            }
        }
    }
}
