package dev.faceless.chat;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;

public interface ChatProcessor {
    Component process(Component message, AsyncChatEvent event);
}

