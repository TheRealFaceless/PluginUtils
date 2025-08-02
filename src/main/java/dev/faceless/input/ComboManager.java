package dev.faceless.input;

import java.util.*;

public class ComboManager {

    @FunctionalInterface
    public interface InputMatcher {
        boolean matches(InputAction action);
    }

    private record TimestampedInput(InputAction input, long timestamp) {}

    public interface ComboListener {
        void onCombo(UUID playerId, String comboName);
    }

    private final Map<UUID, Deque<TimestampedInput>> playerInputs = new HashMap<>();
    private final Map<String, List<InputMatcher>> namedCombos = new HashMap<>();

    private final List<ComboListener> listeners = new ArrayList<>();

    private final long comboWindowMillis;

    public ComboManager(long comboWindowMillis) {
        this.comboWindowMillis = comboWindowMillis;
    }

    public void addCombo(String name, List<InputMatcher> comboPattern) {
        namedCombos.put(name, comboPattern);
    }

    public void recordInput(UUID playerId, InputAction action) {
        long now = System.currentTimeMillis();
        Deque<TimestampedInput> inputs = playerInputs.computeIfAbsent(playerId, k -> new ArrayDeque<>());

        inputs.addLast(new TimestampedInput(action, now));

        while (!inputs.isEmpty() && now - inputs.peekFirst().timestamp > comboWindowMillis) {
            inputs.pollFirst();
        }

        for (Map.Entry<String, List<InputMatcher>> entry : namedCombos.entrySet()) {
            if (matchesCombo(inputs, entry.getValue())) {
                onComboDetected(playerId, entry.getKey());
                inputs.clear();
                break;
            }
        }
    }

    private boolean matchesCombo(Deque<TimestampedInput> inputs, List<InputMatcher> pattern) {
        if (inputs.size() < pattern.size()) return false;

        Iterator<TimestampedInput> it = inputs.descendingIterator();
        for (int i = pattern.size() - 1; i >= 0; i--) {
            InputMatcher matcher = pattern.get(i);
            boolean found = false;

            while (it.hasNext()) {
                TimestampedInput current = it.next();
                if (matcher.matches(current.input)) {
                    found = true;
                    break;
                }
            }

            if (!found) return false;
        }
        return true;
    }


    public void addComboListener(ComboListener listener) {
        listeners.add(listener);
    }

    private void onComboDetected(UUID playerId, String comboName) {
        for (ComboListener listener : listeners) {
            listener.onCombo(playerId, comboName);
        }
    }
}
