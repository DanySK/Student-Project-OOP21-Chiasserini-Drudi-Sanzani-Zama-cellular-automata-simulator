package casim.model.abstraction.automaton;

import java.util.Map;

import casim.model.abstraction.cell.AbstractCell;
import casim.model.abstraction.utils.stats.Stats;
import casim.model.abstraction.utils.stats.StatsImpl;
import casim.utils.grid.Grid2D;

/**
 * Abstract class for {@link Automaton}.
 *
 *  @param <T> the {@link AbstractCell} implementation used by the {@link Automaton}.
 *  @param <S> the states type for the states an {@link Automaton} cell can assume.
 */
public abstract class AbstractAutomaton<S, T extends AbstractCell<S>> implements Automaton<S, T> {

    private int iterationCounter;

    @Override
    public abstract boolean hasNext();

    @Override
    public Grid2D<T> next() {
        this.iterationCounter++;
        return this.doStep();
    }

    /**
     * Method used to obtain the next {@link Automaton} step.
     * 
     * @return a {@link Grid2D} describing the next {@link Automaton} step.
     */
    protected abstract Grid2D<T> doStep();

    @Override
    public abstract Grid2D<T> getGrid();

    @Override
    public Stats<S> getStats() {
        return new StatsImpl<S>(iterationCounter, this.createStatesMap());
    }

    /**
     * Method used to build the map for the cell states stats.
     * 
     * @return the map linking each {@link casim.model.abstraction.cell.Cell}'s state with it's frequency
     *  in the current {@link Automaton} state. 
     */
    protected abstract Map<S, Integer> createStatesMap();

}
