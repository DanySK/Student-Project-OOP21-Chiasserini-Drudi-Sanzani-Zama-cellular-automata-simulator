package casim.controller;

import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.utils.grid.Grid2D;
import casim.model.abstraction.cell.AbstractCell;

/**
 * 
 *
 * @param <T>
 */
public class AutomatonControllerImpl<S, T extends AbstractCell<S>> implements AutomatonController<S> {
    private final AbstractAutomaton<S, T> automaton;

    /**
     * 
     * @param automaton
     */
    public AutomatonControllerImpl(final AbstractAutomaton<S, T> automaton) {
        this.automaton = automaton;
    }

    @Override
    public boolean hasNext() {
        return this.automaton.hasNext();
    }

    @Override
    public Grid2D<S> next() {
        return this.automaton.next().map(AbstractCell::getState);
    }

    @Override
    public Grid2D<S> getGrid() {
        return this.automaton.getGrid().map(AbstractCell::getState);
    }
}
