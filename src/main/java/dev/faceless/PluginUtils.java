package dev.faceless;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginUtils {
    private static JavaPlugin plugin;

    public static void onEnable(JavaPlugin plugin) {
        PluginUtils.plugin = plugin;
    }
}
