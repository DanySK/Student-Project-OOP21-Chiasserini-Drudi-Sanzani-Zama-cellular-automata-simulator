package casim.model.abstraction.cell;

/**
 * An abstract implementation of a {@link Cell}.
 * 
 *  @param <T> the enumeration which contains the finite states of the {@link casim.model.abstraction.automaton.Automaton}'s {@link Cell}.
 */
public abstract class AbstractCell<T> implements Cell<T> {

    protected T state; //TODO improve

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
