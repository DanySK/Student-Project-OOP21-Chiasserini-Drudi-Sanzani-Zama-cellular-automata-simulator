package casim.model.cell;

/**
 * An abstract implementation of a {@link Cell}.
 * 
 *  @param <T> the enumeration which contains the finite states of the {@link casim.model.Automaton}'s {@link Cell}.
 */
public abstract class AbstractCell<T extends Enum<T>> implements Cell<T> {

    private final T state;

    /**
     * Construct a new {@link AbstractCell}.
     * 
     * @param state the current state of the {@link Cell}.
     */
    public AbstractCell(final T state) {
        this.state = state;
    }

    @Override
    public T getState() {
        return this.state;
    }

}
