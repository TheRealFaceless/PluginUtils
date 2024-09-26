package dev.faceless.debug;

import dev.faceless.command.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.stream.Stream;

public class CtfCommand extends Command {

    public CtfCommand() {
        super("ctf", "", "", List.of());
    }

    @ICommand(user = CommandUser.ALL,
            permission = "ctf.regionmode",
            tabCompleter = "regionTabCompleter")
    public void region(CommandContext context) {
        Player player = context.getPlayer();
        assert player != null;

        String[] args = context.args();
        // 0 = region
        // 1 = ?

        if(args.length < 2) return;
        switch (args[1]) {
            case "selectmode" -> player.sendMessage("entered selection mode");
            case "list" -> {
                player.sendMessage("no regions");
            }
            case "save" -> {
                if(args.length != 3) {
                    player.sendMessage(Component.text("Please provide a name!", NamedTextColor.RED));
                    return;
                }
                String name = args[2];
                player.sendMessage("region saved");
            }
        }
    }

    @ITabComplete(name = "regionTabCompleter")
    public List<String> regionTabCompleter(CommandContext context) {
        Player player = context.getPlayer();
        assert player != null;

        String[] args = context.args();
        System.out.println("executing tabcompleter");
        if(args.length == 2) {
            return Stream.of("selectmode", "list", "save").filter(s -> s.startsWith(args[1])).toList();
        }
        return List.of();
    }

    public static void register(JavaPlugin plugin) {
        Command.register(plugin, new CtfCommand());
    }
}
