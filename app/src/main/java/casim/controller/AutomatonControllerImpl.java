package casim.controller;

import casim.model.Automaton;
import casim.utils.grid.Grid2D;
import casim.model.cell.AbstractCell;

/**
 * 
 *
 * @param <T>
 */
public class AutomatonControllerImpl<T> implements AutomatonController<T> {
    private final Automaton<T> automaton;

    /**
     * 
     * @param automaton
     */
    public AutomatonControllerImpl(final Automaton<T> automaton) {
        this.automaton = automaton;
    }

    @Override
    public boolean hasNext() {
        return this.automaton.hasNext();
    }

    @Override
    public Grid2D<T> next() {
        return this.automaton.next().map(AbstractCell::getState);
    }

    @Override
    public Grid2D<T> getGrid() {
        return this.automaton.getGrid().map(AbstractCell::getState);
    }
}
