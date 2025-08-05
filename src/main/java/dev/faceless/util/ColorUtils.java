package dev.faceless.util;

import net.kyori.adventure.text.format.TextColor;

public final class ColorUtils {
    private ColorUtils() {}
    
    public static int toArgb(TextColor color, int alpha) {
        int rgb = color.value();
        return ((alpha & 0xFF) << 24)
             | (rgb & 0x00_FFFF_FF);
    }

    public static int toArgb(TextColor color) {
        return 0xFF000000 | (color.value() & 0x00_FFFF_FF);
    }
}