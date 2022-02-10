package casim.model;

/**
 * An interface which describes the {@link Cell}'s attributes.
 * 
 * Each cell's attributes consist of:
 * - One of the finite states a {@link Cell} can assume;
 * - eventually other attributes as the life-time.
 * 
 * @param <T> the type of the finite states a cell can assume (usually they're represented by an enumeration).
 */
public interface CellAttributes<T> {

    /**
     * Return the current state of the {@link Cell}. 
     *
     * @return the current state of the {@link Cell}.
     */
    T getState();

}
