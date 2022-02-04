package casim.utils.coordinate;

/**
 * Represent a 2D Coordinate.
 * 
 * @param <T> the type of the coordinate system.
 */
public interface Coordinate<T extends Number> {
    /**
     * Set the X coordinate value.
     * 
     * @param value the value to be assigned to the X.
     */
    void setX(T value);

    /**
     * Set the Y coordinate value.
     * 
     * @param value the value to be assigned to the Y.
    */
    void setY(T value);

    /**
     * Get the X coordinate value.
     * 
     * @return the X coordinate value.
     */
    T getX();

    /**
     * Get the Y coordinate value.
     * 
     * @return the Y coordinate value.
     */
    T getY();
}
