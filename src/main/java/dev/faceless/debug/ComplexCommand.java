package dev.faceless.debug;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.faceless.commands.CommandWrapper;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class ComplexCommand extends CommandWrapper {

    public ComplexCommand() {
        super("complex");
    }

    @Override
    protected void buildCommand(LiteralArgumentBuilder<CommandSourceStack> builder) {
        builder.then(Commands.literal("sub")
            .then(Commands.argument("type", StringArgumentType.string())
                .executes(this::executeSubcommand)
                .then(Commands.literal("flag")
                    .executes(this::executeSubcommandWithFlag)
                )
            )
        );

        builder.then(Commands.literal("info")
            .then(Commands.argument("id", IntegerArgumentType.integer())
                .executes(this::executeInfoCommand)
            )
        );
    }

    private int executeSubcommand(com.mojang.brigadier.context.CommandContext<CommandSourceStack> context) {
        String type = StringArgumentType.getString(context, "type");
        CommandSourceStack source = context.getSource();
        source.sendSuccess(() -> Component.literal("Subcommand executed with type: " + type), false);
        return 1;
    }

    private int executeSubcommandWithFlag(com.mojang.brigadier.context.CommandContext<CommandSourceStack> context) {
        String type = StringArgumentType.getString(context, "type");
        CommandSourceStack source = context.getSource();
        source.sendSuccess(() -> Component.literal("Subcommand executed with type: " + type + " and flag set"), false);
        return 1;
    }

    private int executeInfoCommand(com.mojang.brigadier.context.CommandContext<CommandSourceStack> context) {
        int id = IntegerArgumentType.getInteger(context, "id");
        CommandSourceStack source = context.getSource();
        source.sendSuccess(() -> Component.literal("Info command executed with ID: " + id), false);
        return 1;
    }
}
