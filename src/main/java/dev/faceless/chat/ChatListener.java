package dev.faceless.chat;

import dev.faceless.events.GlobalEventHandler;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;

public class ChatListener {
    public static final ChatPipeline chatPipeline = new ChatPipeline();

    public static void register() {
        final GlobalEventHandler eventHandler = GlobalEventHandler.get();

        eventHandler.addListener(AsyncChatEvent.class, chatEvent -> {
            Component processedMessage = chatPipeline.processMessage(chatEvent);
            chatEvent.message(processedMessage);
        });
    }
}
