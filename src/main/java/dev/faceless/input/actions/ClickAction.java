package dev.faceless.input.actions;


import dev.faceless.input.InputAction;
import lombok.Getter;

@Getter
public class ClickAction extends InputAction {
    private final ClickType clickType;
    private final boolean clickedBlock;

    public ClickAction(ClickType clickType, boolean clickedBlock) {
        this.clickType = clickType;
        this.clickedBlock = clickedBlock;
    }

    @Override
    public String getName() {
        return clickType.name() + "_CLICK_" + (clickedBlock ? "BLOCK" : "AIR");
    }


    public enum ClickType {
        LEFT,
        RIGHT
    }
}
