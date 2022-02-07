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
        return null;
    }

    /**
     * Create a new {@link Coordinates} object from the values given as arguments.
     * 
     * @param <T> type of the {@link Coordinates} involved.
     * @param x value to be used as coordinate x.
     * @param y value to be used as coordinate y.
     * @return a {@link Coordinates} with the x and y values given as arguments.
     */
    public static <T extends Number> Coordinates<T> of(final T x, final T y) {
    	return new CoordinatesImpl<T>(x, y);
    }

    /**
     * Checks if the {@link Coordinates} given as argument is inside the rectangle formed by the {@link Coordinates} topLeft and bottomRight.
     * The check is inclusive for the topLeft {@link Coordinates} but not for bottomRight.
     * 
     * @param <T> type of the {@link Coordinates} involved.
     * @param coord the {@link Coordinates} to be checked against topLeft and bottomRight.
     * @param topLeft the {@link Coordinates} representing the point at the top left of the rectangle that coord has to be inside of.
     * @param bottomRight the {@link Coordinates} representing the point at the bottom right of the rectangle that coord has to be inside of.
     * @return True if coord is inside the rectangle, false otherwise.
     */
    public static <T extends Number> boolean  isValid(final Coordinates<T> coord, final Coordinates<T> topLeft, final Coordinates<T> bottomRight) {
    	final var coordD = CoordinatesUtil.of(coord.getX().doubleValue(), coord.getY().doubleValue());
    	final var topLeftD = CoordinatesUtil.of(topLeft.getX().doubleValue(), topLeft.getY().doubleValue());
    	final var bottomRightD = CoordinatesUtil.of(bottomRight.getX().doubleValue(), bottomRight.getY().doubleValue());
    	
    	return coordD.getX() >= topLeftD.getX() && coordD.getY() <= topLeftD.getY() 
				&& coordD.getX() < bottomRightD.getX() && coordD.getY() > bottomRightD.getY();
    }
}
