package dev.faceless.input.actions;

import dev.faceless.input.InputAction;
import lombok.Getter;

@Getter
public class LookAction extends InputAction {
    private final boolean lookedLeft;
    private final boolean lookedRight;
    private final boolean lookedUp;
    private final boolean lookedDown;

    public LookAction(float oldYaw, float oldPitch, float newYaw, float newPitch) {
        float yawDelta = newYaw - oldYaw;
        float pitchDelta = newPitch - oldPitch;

        this.lookedLeft = yawDelta < -2;
        this.lookedRight = yawDelta > 2;

        this.lookedUp = pitchDelta < -2;
        this.lookedDown = pitchDelta > 2;
    }

    @Override
    public String getName() {
        StringBuilder name = new StringBuilder("LOOK_");
        if (lookedUp) name.append("UP_");
        if (lookedDown) name.append("DOWN_");
        if (lookedLeft) name.append("LEFT_");
        if (lookedRight) name.append("RIGHT_");
        if (name.length() == 5) name.append("NONE_");
        return name.substring(0, name.length() - 1);
    }
}

