package com.auster.thatcrapluxiandfischwantedonourserver.commands;

import com.auster.thatcrapluxiandfischwantedonourserver.ThatCrapLuxiandFischWantedOnOurServer;
import com.auster.thatcrapluxiandfischwantedonourserver.utils.PlayerDataUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerDataCommand extends AbstractCommand {
    private static final List<String> TYPES = Arrays.asList("tnt", "aoth_reach");

    public PlayerDataCommand(String command, String permission) {
        super(command, permission);

    }


    @Override
    public List<String> tab(CommandSender sender, Command command, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            Bukkit.getOnlinePlayers().forEach(p1 -> {
                list.add(p1.getName());
            });
        } else if (args.length == 2) {
            list.addAll(TYPES);
        }
        return list;
    }

    @Override
    public void execute(CommandSender sender, Player p, boolean isPlayer, Command command, String[] args) {
        //p.playSound(p.getLocation(), "custom.hitler-nein", 1, 0.f);
        if (args.length == 3) {
            Player t = Bukkit.getPlayer(args[0]);

            if (t == null) {
                sender.sendMessage(ChatColor.RED + "This player doesn't exist");
                return;
            }
            if (TYPES.contains(args[1].toLowerCase())) {
                switch (args[1]) {

                    case "tnt":
                        if (NumberUtils.isNumber(args[2])) {
                            PlayerDataUtils.setTNTExplosionForce(t.getUniqueId(), NumberUtils.toInt(args[2]));
                            sender.sendMessage(ChatColor.GOLD + t.getDisplayName() + ChatColor.GOLD + ": TNT " + args[2]);
                        } else {
                            sender.sendMessage(ChatColor.RED + "Not a valid number!");
                        }
                        break;
                }
            }

        }
    }
}
