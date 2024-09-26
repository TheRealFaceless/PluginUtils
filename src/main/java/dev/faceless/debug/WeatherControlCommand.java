package dev.faceless.debug;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.faceless.command.brigadier.CommandWrapper;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;

public class WeatherControlCommand extends CommandWrapper {

    public WeatherControlCommand() {
        super("weathercontrol");
    }

    @Override
    protected void buildCommand(LiteralArgumentBuilder<CommandSourceStack> builder) {
        builder.requires(source -> source.hasPermission(2))
                .then(Commands.literal("set")
                        .then(Commands.literal("clear").executes(context -> setWeather(context.getSource(), "clear")))
                        .then(Commands.literal("rain").executes(context -> setWeather(context.getSource(), "rain")))
                        .then(Commands.literal("thunder").executes(context -> setWeather(context.getSource(), "thunder"))))
                .then(Commands.literal("query").executes(context -> queryWeather(context.getSource())))
                .executes(context -> suggestWeatherOptions(context.getSource()));
    }

    private static int setWeather(CommandSourceStack source, String weatherType) {
        ServerLevel world = source.getLevel();

        boolean toClear = weatherType.equals("clear");
        boolean toRain = weatherType.equals("rain");
        boolean toThunder = weatherType.equals("thunder");

        if (toClear) world.setWeatherParameters(0, 12000, false, false);
        else if (toRain) world.setWeatherParameters(0, 12000, true, false);
        else if (toThunder) world.setWeatherParameters(0, 12000, true, true);

        source.sendSuccess(() -> Component.literal("Weather set to " + weatherType + ".").withStyle(style -> style.withColor(0x00FF00)), true);
        return 1;
    }

    private static int queryWeather(CommandSourceStack source) {
        ServerLevel world = source.getLevel();
        String weather;

        if (world.isThundering()) weather = "thunder";
        else if (world.isRaining()) weather = "rain";
        else weather = "clear";

        source.sendSuccess(() -> Component.literal("Current weather is " + weather + ".").withStyle(style -> style.withColor(0x00FF00)), false);
        return 1;
    }

    private static int suggestWeatherOptions(CommandSourceStack source) {
        source.sendSuccess(() -> Component.literal("Try /weathercontrol set <clear | rain | thunder>").withStyle(style -> style.withColor(0x00FF00)), false);
        return 1;
    }
}
