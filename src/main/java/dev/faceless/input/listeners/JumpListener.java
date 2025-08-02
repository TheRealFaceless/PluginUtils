package dev.faceless.input.listeners;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import dev.faceless.input.InputManager;
import dev.faceless.input.actions.JumpAction;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class JumpListener implements Listener {

    @EventHandler
    public void onJump(PlayerJumpEvent e) {
        Player player = e.getPlayer();
        Location from = e.getFrom();
        Location to = e.getTo();

        InputManager.record(player, new JumpAction(from, to));
    }
}
