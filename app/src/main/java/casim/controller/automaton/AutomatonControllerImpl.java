package casim.controller.automaton;

import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.utils.grid.Grid2D;
import casim.model.abstraction.cell.AbstractCell;
import casim.model.abstraction.utils.stats.Stats;
import casim.model.abstraction.utils.stats.StatsImpl;

/**
 * Implementation of {@link AutomatonController}.
 *
 * @param <S> the states of the cells.
 * @param <T> the type of the cells.
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

    @Override
    public Stats<S> getStats() {
        return this.automaton.getStats();
    }
}
