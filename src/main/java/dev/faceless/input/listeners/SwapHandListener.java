package dev.faceless.input.listeners;

import dev.faceless.input.InputManager;
import dev.faceless.input.actions.SwapHandAction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class SwapHandListener implements Listener {

    @EventHandler
    public void onSwapHand(PlayerSwapHandItemsEvent e) {
        Player player = e.getPlayer();
        InputManager.record(player, new SwapHandAction());
    }
}
