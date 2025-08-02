package dev.faceless.input.actions;

import dev.faceless.input.InputAction;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.util.Vector;

@Getter
public class MoveAction extends InputAction {
    private final Location from;
    private final Location to;
    private final boolean sprinting;
    private final boolean movedForward;
    private final boolean movedBackward;
    private final boolean movedLeft;
    private final boolean movedRight;

    public MoveAction(Location from, Location to, boolean sprinting) {
        this.from = from.clone();
        this.to = to.clone();
        this.sprinting = sprinting;

        Vector moveVec = to.toVector().subtract(from.toVector());

        float yaw = (from.getYaw() + 90) % 360;
        if (yaw < 0) yaw += 360;
        double yawRad = Math.toRadians(yaw);

        Vector forward = new Vector(Math.cos(yawRad), 0, Math.sin(yawRad));
        Vector right = new Vector(-forward.getZ(), 0, forward.getX());

        double forwardDot = moveVec.dot(forward);
        double rightDot = moveVec.dot(right);

        double threshold = 0.01;

        this.movedForward = forwardDot > threshold;
        this.movedBackward = forwardDot < -threshold;
        this.movedRight = rightDot > threshold;
        this.movedLeft = rightDot < -threshold;
    }

    public boolean isInvalidMovement() {
        return !(isMovedForward() || isMovedBackward() || isMovedLeft() || isMovedRight() || isSprinting());
    }

    @Override
    public String getName() {
        StringBuilder directions = new StringBuilder();

        if (movedForward) directions.append("FORWARD_");
        if (movedBackward) directions.append("BACKWARD_");
        if (movedLeft) directions.append("LEFT_");
        if (movedRight) directions.append("RIGHT_");
        if (sprinting) directions.append("SPRINTING_");

        if (directions.isEmpty()) return "MOVE_INVALID";
        return "MOVE_" + directions.substring(0, directions.length() - 1);
    }

}
