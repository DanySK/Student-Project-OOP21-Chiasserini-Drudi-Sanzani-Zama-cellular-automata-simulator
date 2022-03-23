package casim.model.codi.utils;

import java.util.EnumMap;
import java.util.Random;
import java.util.function.Supplier;

import casim.model.codi.cell.attributes.Direction;
import casim.utils.coordinate.Coordinates3D;
import casim.utils.coordinate.CoordinatesUtil;

/**
 * A static utility class for {@link casim.model.codi.CoDi} rules.
 */
public final class CodiUtils {

    private CodiUtils() {
    }

    /**
     * Return a new {@link EnumMap} filled with a supplier.
     * 
     * @param <T> the type of the values returned by the supplier.
     * @param valuesSupplier the supplier which gives the values to put in each { @link Direction}.
     * @return the new {@link EnumMap}.
     */
    public static <T> EnumMap<Direction, T> newFilledEnumMap(final Supplier<T> valuesSupplier) {
        final EnumMap<Direction, T> map = new EnumMap<>(Direction.class);
        for (final var d: Direction.values()) {
            map.put(d, valuesSupplier.get());
        }
        return map;
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
        return enumMap.values().stream().filter(v -> v == value).reduce((n1, n2) -> n1 + n2).orElse(0);

    }

    /**
     * Return the {@link Coordinates3D} of the neighbours of a cell in a specific {@link Direction}. 
     * 
     * @param coord the {@link Coordinates3D} of the current cell.
     * @param direction the direction of the neighbour.
     * @return the resultant {@link Coordinates3D}.
     */
    public static Coordinates3D<Integer> getNeighbourCoordinates(final Coordinates3D<Integer> coord, final Direction direction) {
        return CoordinatesUtil.sumInt(coord, direction.getDirectionOffset());
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
     * Return true with 25% of probability.
     * 
     * @return true with 25% probability.
     */
    public static boolean rand25() {
        return CodiUtils.rand50() && CodiUtils.rand50();
    } 
}
