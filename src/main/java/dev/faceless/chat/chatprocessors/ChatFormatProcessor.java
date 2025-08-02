package dev.faceless.chat.chatprocessors;

import dev.faceless.chat.ChatProcessor;
import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChatFormatProcessor implements ChatProcessor {

    @Override
    public Component process(Component message, AsyncChatEvent event) {
        event.renderer(new FormatRenderer());
        return message;
    }

    private static class FormatRenderer implements ChatRenderer {
        @Override
        public @NotNull Component render(
                @NotNull Player source,
                @NotNull Component sourceDisplayName,
                @NotNull Component message,
                @NotNull Audience viewer
        ) {
            return Component.text("❖ ", NamedTextColor.GOLD)
                    .append(Component.text(source.getName(), NamedTextColor.YELLOW))
                    .append(Component.text(" ➤ ", NamedTextColor.GOLD))
                    .append(message.color(NamedTextColor.WHITE));
        }
    }
}

