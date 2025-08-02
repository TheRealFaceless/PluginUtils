package dev.faceless.input.listeners;

import dev.faceless.input.InputManager;
import dev.faceless.input.actions.HotbarChangeAction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class HotbarChangeListener implements Listener {

    @EventHandler
    public void onHotbarSwitch(PlayerItemHeldEvent e) {
        int from = e.getPreviousSlot();
        int to = e.getNewSlot();

        if (from == to) return;

        InputManager.record(e.getPlayer(), new HotbarChangeAction(from, to));
    }
}
