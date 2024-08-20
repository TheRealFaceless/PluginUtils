package dev.faceless.events;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dev.faceless.PluginUtils;
import dev.faceless.packet.AbstractPacketListener;
import dev.faceless.packet.PacketManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.function.Consumer;

@SuppressWarnings("unused")
@CanIgnoreReturnValue
public class GlobalEventHandler {
    private static GlobalEventHandler eventHandler;

    private GlobalEventHandler() {}

    public static GlobalEventHandler get() {
        return eventHandler == null ? eventHandler = new GlobalEventHandler() : eventHandler;
    }

    public <T extends Event> GlobalEventHandler addListener(Class<T> eventClass, EventPriority priority, Consumer<T> consumer) {
        final Listener listener = new Listener() {};

        Bukkit.getPluginManager().registerEvent(eventClass, listener, priority, (l, event) -> {
            if (eventClass.isInstance(event)) consumer.accept(eventClass.cast(event));
        }, PluginUtils.getPlugin());

        return this;
    }

    public GlobalEventHandler addListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, PluginUtils.getPlugin());
        return this;
    }

    public GlobalEventHandler addListener(AbstractPacketListener listener) {
        PacketManager.getInstance().registerListener(listener);
        return this;
    }

    public <T extends Event> GlobalEventHandler addListener(Class<T> eventClass, Consumer<T> consumer) {
        addListener(eventClass, EventPriority.NORMAL, consumer);
        return this;
    }
}