package dev.faceless.command.brigadier;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;

public abstract class CommandWrapper {
    private final String name;

    public CommandWrapper(String name) {
        this.name = name;
    }

    protected abstract void buildCommand(LiteralArgumentBuilder<CommandSourceStack> builder);

    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> commandBuilder = LiteralArgumentBuilder.literal(name);
        buildCommand(commandBuilder);
        dispatcher.register(commandBuilder);
    }
}
