package net.vlands.util.internal.java;

import java.util.Arrays;
import java.util.Objects;

public class Validate {

    public static boolean anyNotNull(Object... objects) {
        return Arrays.stream(objects).anyMatch(Objects::nonNull);
    }

    public static boolean anyNull(Object... objects) {
        return Arrays.stream(objects).anyMatch(Objects::isNull);
    }

    public static boolean allNotNull(Object... objects) {
        return Arrays.stream(objects).allMatch(Objects::nonNull);
    }

    public static boolean allNull(Objects... objects) {
        return Arrays.stream(objects).allMatch(Objects::isNull);
    }

}
