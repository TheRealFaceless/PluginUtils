package dev.faceless;

import dev.faceless.command.Command;
import dev.faceless.debug.CtfCommand;
import dev.faceless.events.GlobalEventHandler;
import dev.faceless.listeners.AbstractItemListener;
import dev.faceless.listeners.MenuListener;
import dev.faceless.listeners.PaginatedMenuListener;
import dev.faceless.packet.PacketListenerInjector;
import dev.faceless.storage.yaml.ConfigManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginUtils {
    @Getter private static JavaPlugin plugin;

    public static void onEnable(JavaPlugin plugin) {
        PluginUtils.plugin = plugin;
        ConfigManager.getManager().load(plugin);
        PacketListenerInjector.register();

        AbstractItemListener.init();
        GlobalEventHandler.get()
                .addListener(new PaginatedMenuListener())
                .addListener(new MenuListener());
        //CommandRegistrar.getRegistrar().registerCommand(new ComplexCommand(), new WeatherControlCommand());
        //Command.register(plugin, new CtfCommand());
    }

    public static void onDisable() {
        if(plugin == null) throw new IllegalStateException("Attempting to disable library without ever initializing it.");
    }
}
