package dev.faceless.input;

import lombok.Getter;

@Getter
public abstract class InputAction {
    private final long timestamp;

    protected InputAction() {
        this.timestamp = System.currentTimeMillis();
    }

    public abstract String getName();

    @Override
    public String toString() {
        return getName();
    }
}