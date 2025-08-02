package dev.faceless.input.actions;

import dev.faceless.input.InputAction;
import lombok.Getter;
import org.bukkit.Location;

@Getter
public class JumpAction extends InputAction {
    private final Location from;
    private final Location to;

    public JumpAction(Location from, Location to) {
        this.from = from.clone();
        this.to = to.clone();
    }

    @Override
    public String getName() {
        return "JUMP";
    }
}
