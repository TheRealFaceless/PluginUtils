package dev.faceless.chat.chatprocessors;

import dev.faceless.chat.ChatProcessor;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

import java.util.HashMap;
import java.util.Map;

public class ChatEmojiProcessor implements ChatProcessor {

    private static final Map<String, String> emojiMap = new HashMap<>();

    static {
        emojiMap.put(":smile:", "😊");
        emojiMap.put(":heart:", "❤️");
        emojiMap.put(":thumbsup:", "👍");
        emojiMap.put(":wink:", "😉");
        emojiMap.put(":star:", "⭐");
        emojiMap.put(":fire:", "🔥");
        emojiMap.put(":sunglasses:", "😎");
        emojiMap.put(":laugh:", "😂");
        emojiMap.put(":sad:", "😢");
        emojiMap.put(":angry:", "😠");
        emojiMap.put(":confused:", "😕");
        emojiMap.put(":shrug:", "🤷");
        emojiMap.put(":clap:", "👏");
        emojiMap.put(":wave:", "👋");
        emojiMap.put(":check:", "✅");
        emojiMap.put(":x:", "❌");
        emojiMap.put(":skull:", "💀");
        emojiMap.put(":100:", "💯");
    }

    @Override
    public Component process(Component message, AsyncChatEvent event) {
        if (!(message instanceof TextComponent textComponent)) return message;

        String content = textComponent.content();
        String replaced = replaceEmojiText(content);
        return message.replaceText(builder -> builder.matchLiteral(content).replacement(replaced));
    }

    private static String replaceEmojiText(String text) {
        for (Map.Entry<String, String> entry : emojiMap.entrySet()) {
            text = text.replace(entry.getKey(), entry.getValue());
        }
        return text;
    }
}
