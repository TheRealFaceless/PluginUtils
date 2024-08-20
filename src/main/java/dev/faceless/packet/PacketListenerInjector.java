package dev.faceless.packet;

import dev.faceless.events.GlobalEventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PacketListenerInjector {
    private static final GlobalEventHandler eventHandler = GlobalEventHandler.get();

    public static void register() {
        eventHandler.addListener(PlayerJoinEvent.class, event-> PacketUtils.injectPlayer(event.getPlayer()));
        eventHandler.addListener(PlayerQuitEvent.class, event-> PacketUtils.removePlayer(event.getPlayer()));
    }
}
