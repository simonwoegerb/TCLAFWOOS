package live.auster.staticitems.listeners;

import live.auster.staticitems.StaticItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class WorldInteractionListener implements Listener {
    @EventHandler
    public void interact(PlayerInteractEvent event) {
        if (!event.getItem().getType().isAir() && StaticItems.getInstance().getItemManager().isStaticItem(event.getItem())) {
            var itemstack = event.getItem();
            var x = StaticItems.getInstance().getItemManager().getItem(itemstack);
            Player p = event.getPlayer();
            if (p.isSneaking()) {
                switch (event.getAction()) {
                    case LEFT_CLICK_AIR -> x.shiftLeftClickAirAction(p, itemstack);
                    case RIGHT_CLICK_AIR -> x.shiftRightClickAirAction(p, itemstack);
                    case LEFT_CLICK_BLOCK -> x.shiftLeftClickBlockAction(p, event, event.getClickedBlock(), itemstack);
                    case RIGHT_CLICK_BLOCK ->
                            x.shiftRightClickBlockAction(p, event, event.getClickedBlock(), itemstack);
                    case PHYSICAL -> Bukkit.broadcastMessage("bs");
                }

            } else {
                switch (event.getAction()) {
                    case LEFT_CLICK_AIR -> x.leftClickAirAction(p, itemstack);
                    case RIGHT_CLICK_AIR -> x.rightClickAirAction(p, itemstack);
                    case LEFT_CLICK_BLOCK -> x.leftClickBlockAction(p, event, event.getClickedBlock(), itemstack);
                    case RIGHT_CLICK_BLOCK -> x.rightClickBlockAction(p, event, event.getClickedBlock(), itemstack);
                    case PHYSICAL -> Bukkit.broadcastMessage("bs");

                }
            }
        }
    }


}
