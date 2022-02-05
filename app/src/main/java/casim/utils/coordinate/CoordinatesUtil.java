package casim.utils.coordinate;

/**
 * Utility class that defines methods for the Coordinate type.
 */
public final class CoordinatesUtil {

    private CoordinatesUtil() {

    }

    /**
     * Sum two {@link Coordinates} objects returning a new {@link Coordinates}.
     * 
     * The sum operation generates a {@link Coordinates} that has:
     *  - X equals to the sum of the X values of a and b
     *  - Y equals to the sum of the Y values of a and b
     * 
     * @param <T> type of the {@link Coordinates} involved.
     * @param a first element of the sum.
     * @param b second element of the sum.
     * @return a new {@link Coordinates} object representing the sum of a and b.
     */
    public static <T extends Number> Coordinates<T> sum(final Coordinates<T> a, final Coordinates<T> b) {
        //TODO
        return null;
    }
}
