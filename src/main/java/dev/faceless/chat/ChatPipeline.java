package dev.faceless.chat;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;

public class ChatPipeline {

    private final ArrayList<ChatProcessor> processors = new ArrayList<>();

    public void addProcessor(ChatProcessor processor) {
        processors.add(processor);
    }

    public void removeProcessor(ChatProcessor processor) {
        processors.remove(processor);
    }

    protected Component processMessage(AsyncChatEvent event) {
        Component message = event.message();

        for (ChatProcessor processor : processors) {
            message = processor.process(message, event);
        }

        return message;
    }
}
