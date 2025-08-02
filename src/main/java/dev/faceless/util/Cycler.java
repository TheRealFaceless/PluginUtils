package dev.faceless.util;

import java.util.Arrays;
import java.util.List;

public class Cycler {
    public static <T extends Enum<T>> T nextEnum(T current) {
        T[] values = current.getDeclaringClass().getEnumConstants();
        return values[(current.ordinal() + 1) % values.length];
    }

    public static <T extends Enum<T>> T prevEnum(T current) {
        T[] values = current.getDeclaringClass().getEnumConstants();
        return values[(current.ordinal() - 1 + values.length) % values.length];
    }

    public static <T> T nextFromList(List<T> list, T current) {
        if (list == null || list.isEmpty()) return null;
        int i = list.indexOf(current);
        return list.get((i + 1) % list.size());
    }

    public static <T> T prevFromList(List<T> list, T current) {
        if (list == null || list.isEmpty()) return null;
        int i = list.indexOf(current);
        return list.get((i - 1 + list.size()) % list.size());
    }

    public static <T> T nextFromArray(T[] array, T current) {
        if (array == null || array.length == 0) return null;
        int i = Arrays.asList(array).indexOf(current);
        return array[(i + 1) % array.length];
    }

    public static <T> T prevFromArray(T[] array, T current) {
        if (array == null || array.length == 0) return null;
        int i = Arrays.asList(array).indexOf(current);
        return array[(i - 1 + array.length) % array.length];
    }

    public static int nextIntInRange(int current, int min, int max) {
        if (max <= min) throw new IllegalArgumentException("max must be greater than min");
        return (current < max - 1) ? current + 1 : min;
    }

    public static int prevIntInRange(int current, int min, int max) {
        if (max <= min) throw new IllegalArgumentException("max must be greater than min");
        return (current > min) ? current - 1 : max - 1;
    }

    public static int nextIndex(int current, int max) {
        return (current + 1) % max;
    }

    public static int prevIndex(int current, int max) {
        return (current - 1 + max) % max;
    }

    public static double nextDouble(double current, double[] values) {
        if (values == null || values.length == 0) return current;
        for (int i = 0; i < values.length; i++) {
            if (Double.compare(values[i], current) == 0) {
                return values[(i + 1) % values.length];
            }
        }
        return values[0];
    }

    public static double prevDouble(double current, double[] values) {
        if (values == null || values.length == 0) return current;
        for (int i = 0; i < values.length; i++) {
            if (Double.compare(values[i], current) == 0) {
                return values[(i - 1 + values.length) % values.length];
            }
        }
        return values[0];
    }

}
