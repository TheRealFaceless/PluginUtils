package dev.faceless.command.brigadier;

import com.mojang.brigadier.CommandDispatcher;
import dev.faceless.PluginUtils;
import lombok.Getter;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

@Getter
public class CommandRegistrar {
    private final JavaPlugin plugin;
    private final MinecraftServer server;
    private final CommandDispatcher<CommandSourceStack> dispatcher;
    private final CommandBuildContext commandbuildcontext = Commands.createValidationContext(VanillaRegistries.createLookup());

    private static CommandRegistrar registrar;

    private CommandRegistrar() {
        this.plugin = PluginUtils.getPlugin();
        this.server = ((CraftServer) Bukkit.getServer()).getServer();
        this.dispatcher = server.getCommands().getDispatcher();
    }

    public void registerCommand(CommandWrapper... wrappers) {
        Arrays.stream(wrappers).forEach((commandWrapper)
                -> Bukkit.getScheduler().runTask(plugin, () -> commandWrapper.register(dispatcher, commandbuildcontext)));
    }

    public static CommandRegistrar getRegistrar() {
        return registrar == null ? registrar = new CommandRegistrar() : registrar;
    }
}
