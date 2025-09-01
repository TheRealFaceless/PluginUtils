package dev.faceless.events;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dev.faceless.PluginUtils;
import dev.faceless.packet.AbstractPacketListener;
import dev.faceless.packet.PacketManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.*;
import java.util.function.Consumer;

@CanIgnoreReturnValue
public class GlobalEventHandler {
    private static GlobalEventHandler eventHandler;

    private final Map<String, Set<Listener>> bukkitListeners = new HashMap<>();
    private final Map<String, Set<AbstractPacketListener>> packetListeners = new HashMap<>();

    private GlobalEventHandler() {}

    public static GlobalEventHandler get() {
        return eventHandler == null ? (eventHandler = new GlobalEventHandler()) : eventHandler;
    }

    public <T extends Event> GlobalEventHandler addListener(String id, Class<T> eventClass, EventPriority priority, Consumer<T> consumer) {
        Listener listener = new Listener() {};
        Bukkit.getPluginManager().registerEvent(eventClass, listener, priority, (l, event) -> {
            if (eventClass.isInstance(event)) {
                consumer.accept(eventClass.cast(event));
            }
        }, PluginUtils.getPlugin());

        bukkitListeners.computeIfAbsent(id, k -> new HashSet<>()).add(listener);
        return this;
    }

    public GlobalEventHandler addListener(String id, Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, PluginUtils.getPlugin());
        bukkitListeners.computeIfAbsent(id, k -> new HashSet<>()).add(listener);
        return this;
    }

    public GlobalEventHandler addListener(String id, AbstractPacketListener listener) {
        PacketManager.getInstance().registerListener(listener);
        packetListeners.computeIfAbsent(id, k -> new HashSet<>()).add(listener);
        return this;
    }

    public <T extends Event> GlobalEventHandler addListener(String id, Class<T> eventClass, Consumer<T> consumer) {
        return this.addListener(id, eventClass, EventPriority.NORMAL, consumer);
    }

    public <T extends Event> GlobalEventHandler addListener(Class<T> eventClass, EventPriority priority, Consumer<T> consumer) {
        return addListener(dummyId(), eventClass, priority, consumer);
    }

    public GlobalEventHandler addListener(Listener listener) {
        return addListener(dummyId(), listener);
    }

    public GlobalEventHandler addListener(AbstractPacketListener listener) {
        return addListener(dummyId(), listener);
    }

    public <T extends Event> GlobalEventHandler addListener(Class<T> eventClass, Consumer<T> consumer) {
        return addListener(dummyId(), eventClass, consumer);
    }

    private String dummyId() {
        return "UNNAMED-" + UUID.randomUUID();
    }

    public void unregister(String id) {
        Set<Listener> bukkit = bukkitListeners.remove(id);
        if (bukkit != null) {
            for (Listener listener : bukkit) {
                HandlerList.unregisterAll(listener);
            }
        }

        Set<AbstractPacketListener> packets = packetListeners.remove(id);
        if (packets != null) {
            for (AbstractPacketListener listener : packets) {
                PacketManager.getInstance().unregisterListener(listener);
            }
        }
    }

    public void unregisterAll() {
        for (Set<Listener> set : bukkitListeners.values()) {
            for (Listener listener : set) {
                HandlerList.unregisterAll(listener);
            }
        }
        bukkitListeners.clear();

        for (Set<AbstractPacketListener> set : packetListeners.values()) {
            for (AbstractPacketListener listener : set) {
                PacketManager.getInstance().unregisterListener(listener);
            }
        }
        packetListeners.clear();
    }
}
