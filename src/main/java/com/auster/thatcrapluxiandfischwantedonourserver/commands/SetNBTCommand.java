package com.auster.thatcrapluxiandfischwantedonourserver.commands;

import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.enchants.CustomEnchants;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.enchants.Telekinesis;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetNBTCommand extends AbstractCommand {
    public SetNBTCommand(String command, String permission) {
        super(command, permission);
    }

    @Override
    public List<String> tab(CommandSender sender, Command command, String[] args) {
        return null;
    }

    @Override
    public void execute(CommandSender sender, Player p, boolean isPlayer, Command command, String[] args) {
        sender.sendMessage("called");
        if (isPlayer) {
            var item = p.getInventory().getItemInMainHand();
            if (item.getType().isAir()) {
                p.sendMessage(ChatColor.RED + "Invalid Item");
                return;
            }
            if (args.length == 1) {
                if (p.getInventory().getItemInMainHand() == null) return;
                NBTItem item1 = new NBTItem(p.getInventory().getItemInMainHand());
                p.sendMessage(item1.getCompound(CustomEnchants.CUSTOMENCHANTS).getInteger(CustomEnchants.getEnchantment(Telekinesis.class).getName()).toString());

            }
            if (args.length == 0) {
                Map<String, String> values = new HashMap<>();
                NBTItem nbtItem = new NBTItem(item);
                nbtItem.getKeys().forEach(s -> {

                    switch (nbtItem.getType(s)) {
                        case NBTTagInt:
                            values.put(s, String.valueOf(nbtItem.getInteger(s)));
                            break;
                        case NBTTagByte:
                            values.put(s, String.valueOf(nbtItem.getByte(s)));
                            break;
                        case NBTTagLong:
                            values.put(s, String.valueOf(nbtItem.getLong(s)));
                            break;
                        case NBTTagFloat:
                            values.put(s, String.valueOf(nbtItem.getFloat(s)));
                            break;
                        case NBTTagShort:
                            values.put(s, String.valueOf(nbtItem.getShort(s)));
                            break;
                        case NBTTagDouble:
                            values.put(s, String.valueOf(nbtItem.getDouble(s)));
                            break;
                        case NBTTagString:
                            values.put(s, nbtItem.getString(s));
                            break;
                        case NBTTagIntArray:
                            StringBuilder builder = new StringBuilder();
                            for (int i :
                                    nbtItem.getIntArray(s)) {
                                builder.append(i).append(" ");
                            }
                            values.put(s, builder.toString());
                            break;
                        case NBTTagByteArray:
                            StringBuilder builder1 = new StringBuilder();
                            for (byte i :
                                    nbtItem.getByteArray(s)) {
                                builder1.append(i).append(" ");
                            }
                            values.put(s, builder1.toString());
                            break;
                    }
                    p.sendMessage(values.size() + "");

                    values.forEach((x, y) -> p.sendMessage(x + " : " + y));
                });

            }

        }
    }
}
