package dev.faceless.input.listeners;

import dev.faceless.input.InputManager;
import dev.faceless.input.actions.ClickAction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class MouseListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        if(e.getHand() == null) return;
        if(!e.getHand().equals(EquipmentSlot.HAND)) return;

        if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            boolean clickedBlock = (action == Action.LEFT_CLICK_BLOCK);
            InputManager.record(player, new ClickAction(ClickAction.ClickType.LEFT, clickedBlock));
        } else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            boolean clickedBlock = (action == Action.RIGHT_CLICK_BLOCK);
            InputManager.record(player, new ClickAction(ClickAction.ClickType.RIGHT, clickedBlock));
        }
    }
}
