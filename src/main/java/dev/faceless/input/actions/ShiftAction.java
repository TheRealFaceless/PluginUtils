package dev.faceless.input.actions;

import dev.faceless.input.InputAction;
import lombok.Getter;

@Getter
public class ShiftAction extends InputAction {
    private final boolean isShifting;

    public ShiftAction(boolean isShifting) {
        this.isShifting = isShifting;
    }

    @Override
    public String getName() {
        return "SHIFT_" + (isShifting ? "DOWN" : "UP");
    }
}
