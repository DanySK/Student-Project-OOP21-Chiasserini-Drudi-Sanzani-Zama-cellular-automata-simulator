package casim.utils.coordinate;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Represents a 2D Coordinate.
 *
 * @param <T> the type of the coordinate system.
 */
public class Coordinates2D<T extends Number> implements Coordinates<T> {

    private final Pair<T, T> coords;
    
    Coordinates2D(final T x, final T y) {
        this.coords = Pair.of(x, y);
    }

    /**
     * Get the X coordinate value.
     * 
     * @return the X coordinate value.
     */
    T getX() {
        return this.coords.getLeft();
    }

    /**
     * Get the Y coordinate value.
     * 
     * @return the Y coordinate value.
     */
    T getY() {
        return this.coords.getRight();
    }
    
}
