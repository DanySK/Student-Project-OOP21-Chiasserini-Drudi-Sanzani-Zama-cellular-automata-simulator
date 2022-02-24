package casim.model.cell;

/**
 * A {@link Cell} of the {@link Grid}.
 * 
 * @param <T> the type of the finite states a {@link Cell} can assume.
 */
public interface Cell<T> {

    /** 
     * Return the current state of the {@link Cell}.
     * 
     * @return the current state of the {@link Cell}.
     */
    T getState();

}
