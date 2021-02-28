package com.auster.thatcrapluxiandfischwantedonourserver.commands;

import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.enchants.CustomEnchants;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items.CustomItems;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class GiveCustomItemCommand extends AbstractCommand {
    public GiveCustomItemCommand(String command, String permission) {
        super(command, permission);
    }

    @Override
    public List<String> tab(CommandSender sender, Command command, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], CustomItems.getClasses(), list);
        } else {
            list.add("1");
            list.add("64");
        }
        return list;
    }

    @Override
    public void execute(CommandSender sender, Player p, boolean isPlayer, Command command, String[] args) {
        if (isPlayer) {
            if (args.length == 1) {
                if (CustomItems.getClasses().contains(args[0])) {
                    var clazz = CustomItems.getNames().get(args[0]);
                    int amount = 1;
                    if (CustomItems.getItem(clazz).stackable) {
                        amount = CustomItems.getItem(clazz).material.getMaxStackSize();
                    }

                    p.getInventory().addItem(CustomItems.createItem(clazz,amount ));


                }
            } else if (args.length == 2 && NumberUtils.isNumber(args[1])) {
                if (CustomItems.getClasses().contains(args[0])) {
                    var clazz = CustomItems.getNames().get(args[0]);

                    p.getInventory().addItem(CustomItems.createItem(clazz, NumberUtils.createInteger(args[1])));


                }
            }
        }
    }
}
