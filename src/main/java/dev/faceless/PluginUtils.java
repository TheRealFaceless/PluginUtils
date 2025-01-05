package dev.faceless;

import dev.faceless.events.GlobalEventHandler;
import dev.faceless.listeners.AbstractItemListener;
import dev.faceless.listeners.MenuListener;
import dev.faceless.listeners.PaginatedMenuListener;
import dev.faceless.packet.PacketListenerInjector;
import dev.faceless.storage.yaml.ConfigManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginUtils {
    @Getter private static JavaPlugin plugin;
    @Getter @Setter private static boolean enableConfigManager = false;
    @Getter @Setter private static boolean enablePacketListener = false;
    @Getter @Setter private static boolean enableItemListener = false;
    @Getter @Setter private static boolean enableMenuListeners = true;

    public static void onEnable(JavaPlugin plugin) {
        PluginUtils.plugin = plugin;
        if (enableConfigManager) ConfigManager.getManager().load(plugin);
        if (enablePacketListener) PacketListenerInjector.register();
        if (enableItemListener) AbstractItemListener.init();
        if (enableMenuListeners) GlobalEventHandler.get().addListener(new PaginatedMenuListener()).addListener(new MenuListener());
    }
}
