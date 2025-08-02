package dev.faceless.util;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Component {
    public enum TextFormat{LEGACY, MINI_MESSAGE}

    public static net.kyori.adventure.text.Component formatLegacy(String text) {
        net.kyori.adventure.text.Component component = LegacyComponentSerializer.legacyAmpersand().deserialize(text);
        return removeItalics(component);
    }

    public static net.kyori.adventure.text.Component formatMini(String text) {
        net.kyori.adventure.text.Component component = MiniMessage.miniMessage().deserialize(text);
        return removeItalics(component);
    }

    public static net.kyori.adventure.text.Component format(String text, TextFormat format) {
        net.kyori.adventure.text.Component component = switch (format) {
            case LEGACY -> formatLegacy(text);
            case MINI_MESSAGE -> formatMini(text);
        };
        return removeItalics(component);
    }

    private static net.kyori.adventure.text.Component removeItalics(net.kyori.adventure.text.Component component) {
        if(!component.hasDecoration(TextDecoration.ITALIC)) {
            component = component.decoration(TextDecoration.ITALIC, false);
        }
        return component;
    }

    public static net.kyori.adventure.text.Component text(String content) {
        net.kyori.adventure.text.Component component = net.kyori.adventure.text.Component.text(content);
        return removeItalics(component);
    }

    public static net.kyori.adventure.text.Component text(String content, NamedTextColor textColor) {
        net.kyori.adventure.text.Component component = net.kyori.adventure.text.Component.text(content, textColor);
        return removeItalics(component);
    }

    public @Nullable
    static String getContent(net.kyori.adventure.text.Component component) {
        if(component instanceof TextComponent textComponent) return textComponent.content();
        return null;
    }

    public static boolean isComponentEmpty(net.kyori.adventure.text.Component component) {
        if (component == null) return true;

        String content = component instanceof TextComponent
                ? ((TextComponent) component).content()
                : component.toString();
        return content.trim().isEmpty() && component.children().isEmpty();
    }

    public static void trimTrailingEmptyLore(List<net.kyori.adventure.text.Component> lore) {
        if (lore == null || lore.isEmpty()) return;

        int lastNonEmpty = -1;
        for (int i = lore.size() - 1; i >= 0; i--) {
            if (!isComponentEmpty(lore.get(i))) {
                lastNonEmpty = i;
                break;
            }
        }

        List<net.kyori.adventure.text.Component> trimmed = new ArrayList<>();
        for (int i = 0; i <= lastNonEmpty; i++) {
            trimmed.add(lore.get(i));
        }

        lore.clear();
        lore.addAll(trimmed);
    }
}
