package dev.faceless.util;

import net.minecraft.network.chat.Component;

import java.util.function.Supplier;

public class NmsUtils {

    public static Supplier<Component> supplierLiteral(String message) {
        return ()-> Component.literal(message);
    }

    public static Component literal(String message) {
        return Component.literal(message);
    }
}
