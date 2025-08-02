package dev.faceless.input.actions;

import dev.faceless.input.InputAction;
import lombok.Getter;

@Getter
public class HotbarChangeAction extends InputAction {
    private final int fromSlot;
    private final int toSlot;
    private final boolean scrolledUp;

    public HotbarChangeAction(int fromSlot, int toSlot) {
        this.fromSlot = fromSlot;
        this.toSlot = toSlot;

        int delta = (fromSlot - toSlot + 9) % 9;
        this.scrolledUp = delta > 0 && delta <= 4;
    }

    @Override
    public String getName() {
        return "SCROLL_" + (scrolledUp ? "UP" : "DOWN") + "/" + fromSlot + "→" + toSlot;
    }
}
