package dev.faceless.input.listeners;

import dev.faceless.input.InputManager;
import dev.faceless.input.actions.LookAction;
import dev.faceless.input.actions.MoveAction;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location from = e.getFrom();
        Location to = e.getTo();

        if (from.equals(to)) return;

        boolean moved = from.getX() != to.getX() || from.getZ() != to.getZ(); // horizontal movement only
        boolean looked = from.getYaw() != to.getYaw() || from.getPitch() != to.getPitch();

        if (moved) {
            MoveAction moveAction = new MoveAction(from, to, p.isSprinting());
            if(!moveAction.isInvalidMovement()) InputManager.record(p, moveAction);
        }
        if (looked) {
            LookAction lookAction = new LookAction(from.getYaw(), from.getPitch(), to.getYaw(), to.getPitch());
            boolean noDirection = !lookAction.isLookedLeft() && !lookAction.isLookedRight()
                    && !lookAction.isLookedUp() && !lookAction.isLookedDown();
            if (!noDirection) InputManager.record(p, lookAction);
        }
    }
}
