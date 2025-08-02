package dev.faceless.menu;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class ChatInputService {
    private final Map<UUID, Consumer<String>> callbacks = new ConcurrentHashMap<>();

    public void prompt(Player player, Component promptText, Consumer<String> onComplete) {
        callbacks.put(player.getUniqueId(), onComplete);
        player.sendMessage(promptText);
        player.closeInventory();
    }

    public void handleChatEvent(Player player, AsyncChatEvent event) {
        Consumer<String> cb = callbacks.remove(player.getUniqueId());
        String message = dev.faceless.util.Component.getContent(event.message());
        if(message == null) throw new IllegalStateException("message is null at " + this.getClass());
        if(message.equalsIgnoreCase("cancel")) {
            cb.accept(message);
            return;
        }
        if (cb == null) return;
        cb.accept(message.trim());
    }
}
