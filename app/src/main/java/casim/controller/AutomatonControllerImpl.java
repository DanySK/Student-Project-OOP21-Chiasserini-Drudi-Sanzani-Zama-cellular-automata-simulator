package casim.controller;

import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.utils.grid.Grid2D;
import casim.model.abstraction.cell.AbstractCell;

/**
 * 
 *
 * @param <T>
 */
public class AutomatonControllerImpl<T extends Enum<T>> implements AutomatonController<T> {
    private final AbstractAutomaton<T> automaton;

    /**
     * 
     * @param automaton
     */
    public AutomatonControllerImpl(final AbstractAutomaton<T> automaton) {
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
