package za.co.no9.util;

import java.util.Optional;

public class IntegerUtils {
    public static Optional<Integer> parseInt(String intString) {
        try {
            return Optional.of(Integer.parseInt(intString));
        } catch (NullPointerException | NumberFormatException ignored) {
            return Optional.empty();
        }
    }
}
