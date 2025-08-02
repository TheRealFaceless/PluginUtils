package dev.faceless.input.listeners;

import dev.faceless.input.InputManager;
import dev.faceless.input.actions.ShiftAction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class ShiftListener implements Listener {

    @EventHandler
    public void onShiftToggle(PlayerToggleSneakEvent e) {
        Player player = e.getPlayer();
        InputManager.record(player, new ShiftAction(e.isSneaking()));
    }
}
