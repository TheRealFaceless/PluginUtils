package dev.faceless.input;

import lombok.Getter;
import org.bukkit.entity.Player;

public class InputManager {
    @Getter private static final ComboManager comboManager = new ComboManager(1000);

    public static void record(Player player, InputAction action) {
        comboManager.recordInput(player.getUniqueId(), action);
    }
}
