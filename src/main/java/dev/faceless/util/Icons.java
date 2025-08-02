package dev.faceless.util;

public final class Icons {
    private Icons() {}

    // ✔✖ ✅❌ for status
    public static final String CHECK           = "✔";    // U+2714
    public static final String CROSS           = "✖";    // U+2716
    public static final String OK              = "✅";   // U+2705
    public static final String NO              = "❌";   // U+274C
    public static final String WARN            = "⚠";    // U+26A0
    public static final String ERROR           = "❗";    // U+2757
    public static final String SUCCESS         = "✔️";   // U+2714 U+FE0F

    // Arrows
    public static final String ARROW_UP        = "↑";    // U+2191
    public static final String ARROW_DOWN      = "↓";    // U+2193
    public static final String ARROW_LEFT      = "←";    // U+2190
    public static final String ARROW_RIGHT     = "→";    // U+2192
    public static final String ARROW_UP_RIGHT  = "↗";    // U+2197
    public static final String ARROW_UP_LEFT   = "↖";    // U+2196
    public static final String ARROW_DOWN_RIGHT= "↘";    // U+2198
    public static final String ARROW_DOWN_LEFT = "↙";    // U+2199
    public static final String ARROW_DOUBLE    = "»";    // U+00BB

    // Shapes & bullets
    public static final String BULLET           = "•";   // U+2022
    public static final String DOT              = "·";   // U+00B7
    public static final String TRIANGLE         = "▶";   // U+25B6
    public static final String TRIANGLE_LEFT    = "◀";   // U+25C0
    public static final String TRIANGLE_UP      = "▲";   // U+25B2
    public static final String TRIANGLE_DOWN    = "▼";   // U+25BC
    public static final String CIRCLE_FILL      = "●";   // U+25CF
    public static final String CIRCLE_EMPTY     = "○";   // U+25CB
    public static final String CIRCLE_HALF_LEFT = "◐";   // U+25D0
    public static final String CIRCLE_HALF_RIGHT= "◑";   // U+25D1
    public static final String CIRCLE_TRIANGLE  = "◬";   // U+25EC
    public static final String SQUARE_FILL      = "■";   // U+25A0
    public static final String SQUARE_EMPTY     = "□";   // U+25A1
    public static final String SQUARE_ROUND     = "▢";   // U+25A2
    public static final String DIAMOND          = "♦";   // U+2666
    public static final String LOZENGE          = "◊";   // U+25CA
    public static final String STAR_FILL        = "★";   // U+2605
    public static final String STAR_EMPTY       = "☆";   // U+2606
    public static final String ASTERISK         = "✽";   // U+273D
    public static final String FLOWER           = "❀";   // U+2740
    public static final String SPIRAL           = "❁";   // U+2741
    public static final String FOUR_TRIANGLE    = "◶";   // U+25F6
    public static final String BOX_DIAGONAL     = "▰";   // U+25B0
    public static final String BOX_ALTERNATE    = "▮";   // U+25AE
    public static final String HEXAGON          = "⬢";   // U+2B22
    public static final String OCTAGON          = "⯃";   // U+2BE3
    public static final String PENTAGON         = "⬟";   // U+2B1F
    public static final String CUBE             = "🞜";   // U+1F79C (may vary)
    public static final String HOURGLASS        = "⌛";   // U+231B
    public static final String SNAKE_TRIANGLE   = "▹";   // U+25B9
    public static final String BULLET_SQUARE    = "▪";   // U+25AA
    public static final String BULLET_LARGE     = "◦";   // U+25E6

    // UI & common
    public static final String PENCIL          = "✏";    // U+270F
    public static final String PAINTBRUSH      = "🖌";    // U+1F58C
    public static final String GEAR            = "⚙";    // U+2699
    public static final String KEYBOARD        = "⌨";    // U+2328
    public static final String SCROLL          = "📜";    // U+1F4DC
    public static final String COMPASS         = "🧭";    // U+1F9ED
    public static final String CLOCK           = "⏰";    // U+23F0
    public static final String CALENDAR        = "📅";    // U+1F4C5
    public static final String INFO            = "ℹ";    // U+2139
    public static final String PLUS = "➕";  // U+2795

    // Combat
    public static final String SWORD           = "⚔";    // U+2694
    public static final String SHIELD          = "🛡";    // U+1F6E1
    public static final String BOW             = "🏹";    // U+1F3F9
    public static final String SKULL           = "☠";    // U+2620
    public static final String FIRE            = "🔥";    // U+1F525
    public static final String BOMB            = "💣";    // U+1F4A3

    // Elements / nature
    public static final String WATER           = "💧";    // U+1F4A7
    public static final String LEAF            = "🍃";    // U+1F343
    public static final String SNOWFLAKE       = "❄";    // U+2744
    public static final String SUN             = "☀";    // U+2600
    public static final String MOON            = "🌙";    // U+1F319
    public static final String LIGHTNING       = "⚡";    // U+26A1

    // Travel & transport
    public static final String BOAT            = "⛵";    // U+26F5
    public static final String MINECART        = "🚂";    // U+1F682
    public static final String AIRPLANE        = "✈";    // U+2708
    public static final String CAR             = "🚗";    // U+1F697

    // Currency & economy
    public static final String COIN            = "🪙";    // U+1FA99 (modern Minecraft font support)
    public static final String MONEY_BAG       = "💰";    // U+1F4B0
    public static final String BANK            = "🏦";    // U+1F3E6

    // Miscellaneous
    public static final String MUSIC           = "♪";    // U+266A
    public static final String MUSIC_DOUBLE    = "♫";    // U+266B
    public static final String QUESTION        = "?";    // simple
    public static final String EXCLAMATION     = "!";    // simple
    public static final String ELLIPSIS        = "…";    // U+2026

    /** Helper to prefix an icon to text. */
    public static String with(String icon, String text) {
        return icon + " " + text;
    }
}
