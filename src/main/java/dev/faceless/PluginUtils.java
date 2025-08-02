package dev.faceless;

import dev.faceless.chat.ChatListener;
import dev.faceless.chat.chatprocessors.ChatEmojiProcessor;
import dev.faceless.chat.chatprocessors.ChatFormatProcessor;
import dev.faceless.command.brigadier.CommandRegistrar;
import dev.faceless.events.GlobalEventHandler;
import dev.faceless.input.InputManager;
import dev.faceless.input.actions.*;
import dev.faceless.input.listeners.*;
import dev.faceless.menu.MenuListener;
import dev.faceless.packet.PacketListenerInjector;
import dev.faceless.resourcepack.PackCommand;
import dev.faceless.resourcepack.PackManager;
import dev.faceless.resourcepack.PackUpdateListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * Must be initialized before any method is called via PluginUtils.init();
 */
public class PluginUtils {
    @Getter private static JavaPlugin plugin;
    @Getter private static PackManager packManager;

    public static void init(JavaPlugin plugin) {
        PluginUtils.plugin = plugin;
    }

    public static void enablePackHosting() {
        packManager = new PackManager(plugin);
        PackUpdateListener.register();
        CommandRegistrar.getRegistrar().registerCommand(new PackCommand());
    }

    public static void enablePacketInjector() {
        PacketListenerInjector.register();
    }

    public static void enableMenuApi() {
        GlobalEventHandler.get().addListener(new MenuListener());
    }

    public static void enableChatModifier(boolean registerDefaultModifiers) {
        ChatListener.register();

        if(registerDefaultModifiers) {
            ChatListener.chatPipeline.addProcessor(new ChatFormatProcessor());
            ChatListener.chatPipeline.addProcessor(new ChatEmojiProcessor());
        }
    }

    public static void enableInputListener(boolean registerDebugCombos) {
        GlobalEventHandler.get()
                .addListener(new MovementListener())
                .addListener(new ShiftListener())
                .addListener(new SwapHandListener())
                .addListener(new MouseListener())
                .addListener(new JumpListener())
                .addListener(new HotbarChangeListener());

        if(registerDebugCombos) registerDebugCombos();
    }

    private static void registerDebugCombos() {
        InputManager.getComboManager().addCombo("Shift + jump + click x2", List.of(
                action -> action instanceof ShiftAction && ((ShiftAction) action).isShifting(),
                action -> action instanceof JumpAction,
                action -> action instanceof ClickAction && ((ClickAction) action).getClickType().equals(ClickAction.ClickType.LEFT) && !((ClickAction) action).isClickedBlock(),
                action -> action instanceof ClickAction && ((ClickAction) action).getClickType().equals(ClickAction.ClickType.LEFT) && !((ClickAction) action).isClickedBlock()
        ));

        InputManager.getComboManager().addCombo("Scroll Up + Click Right", List.of(
                action -> action instanceof HotbarChangeAction && ((HotbarChangeAction) action).isScrolledUp(),
                action -> action instanceof ClickAction && ((ClickAction) action).getClickType() == ClickAction.ClickType.RIGHT
        ));

        InputManager.getComboManager().addCombo("Swap + Jump", List.of(
                action -> action instanceof SwapHandAction,
                action -> action instanceof JumpAction
        ));

        InputManager.getComboManager().addCombo("Scroll Down + Shift", List.of(
                action -> action instanceof HotbarChangeAction && !((HotbarChangeAction) action).isScrolledUp(),
                action -> action instanceof ShiftAction && ((ShiftAction) action).isShifting()
        ));

        InputManager.getComboManager().addComboListener((playerId, comboName) -> {
            Player player = Bukkit.getPlayer(playerId);
            if (player != null) player.sendMessage("Combo detected: " + comboName);
        });
    }

}
