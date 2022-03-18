package casim.model.codi.rule;

import java.util.EnumMap;
import java.util.Random;

import casim.model.codi.Direction;

/**
 * A static utility class for {@link casim.model.codi.CoDi} rules.
 */
public final class RulesUtils {

    private RulesUtils() {
    }

    /**
     * Return a new {@link EnumMap} filled with the specified value.
     * 
     * @param value value to put in each { @link Direction}.
     * @return the new {@link EnumMap}.
     */
    public static EnumMap<Direction, Integer> newFilledEnumMap(final int value) {
        final EnumMap<Direction, Integer> neighborsPreviousInput = new EnumMap<>(Direction.class);
        for (final var d: Direction.values()) {
            neighborsPreviousInput.put(d, value);
        }
        return neighborsPreviousInput;
    }

    /**
     * Return the sum of all the values contained in a {@link EnumMap}.
     * 
     * @param enumMap the enumMap of which the method calculates the sum.
     * @return the sum of all the values.
     */
    public static int sumEnumMapValues(final EnumMap<Direction, Integer> enumMap) {
        return enumMap.values().stream().reduce((n1, n2) -> n1 + n2).get();
    }

    /**
     * Return the sum of all the values of a specific type in a {@link EnumMap}.
     * 
     * @param enumMap the enumMap of which the method calculates the sum.
     * @param value the specific value used by the method.
     * @return the sum of the values.
     */
    public static int sumEnumMapSpecificValues(final EnumMap<Direction, Integer> enumMap, final int value) {
        return enumMap.values().stream().filter(v -> v == value).reduce((n1, n2) -> n1 + n2).get();

    }

    /**
     * Return true with 50% of probability.
     * 
     * @return true or false with equal probability.
     */
    public static boolean rand50() {
        final Random random = new Random();
        return random.nextInt() % 2 == 0;
    }

    /**
     * Return true with 25% of probabiblity.
     * 
     * @return true with 25% probabiilty.
     */
    public static boolean rand75() {
        return RulesUtils.rand50() && RulesUtils.rand50();
    } 
}
