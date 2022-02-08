package casim.utils.coordinate;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Represents a 2D coordinate.
 *
 * @param <T> the type of the coordinate system.
 */
public class CoordinatesImpl<T extends Number> implements Coordinates<T> {

    private Pair<T, T> coords;

    /**
     * @param x the value that will be assigned to the x coordinate.
     * @param y the value that will be assigned to the y coordinate.
     */
    public CoordinatesImpl(final T x, final T y) {
        this.coords = new ImmutablePair<>(x, y);
    }

    @Override
    public void setX(final T value) {
        this.coords = new ImmutablePair<>(value, this.coords.getRight());
    }

    @Override
    public void setY(final T value) {
        this.coords = new ImmutablePair<>(this.coords.getLeft(), value);
    }

    @Override
    public T getX() {
        return this.coords.getLeft();
    }

    @Override
    public T getY() {
        return this.coords.getRight();
    }

}
