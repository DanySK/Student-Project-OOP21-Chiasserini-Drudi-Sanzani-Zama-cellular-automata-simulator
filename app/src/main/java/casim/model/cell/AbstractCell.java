package casim.model.cell;

/**
 * An abstract implementation of a {@Cell}.
 * 
 *  @param <T> the type of the finite states of the {@link Automaton}'s {@link Cell}.
 */
public abstract class AbstractCell<T> implements Cell<T> {

    private final T state;

    /**
     * Construct a new {@link AbstractCell}.
     * 
     * @param state the current state of the {@link Cell}.
     */
    public AbstractCell(final T state) {
        this.state = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getState() {
        return this.state;
    }

}
