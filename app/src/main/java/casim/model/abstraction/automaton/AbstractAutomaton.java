package casim.model.abstraction.automaton;

import java.util.EnumMap;

import casim.model.abstraction.cell.AbstractCell;
import casim.model.abstraction.utils.stats.Stats;
import casim.model.abstraction.utils.stats.StatsImpl;
import casim.utils.grid.Grid2D;

/**
 * Abstract class for {@link Automaton}.
 *
 * @param <T> the enumeration which contains the finite states of the {@link Automaton}'s {@link casim.model.abstraction.cell.Cell}.
 */
public abstract class AbstractAutomaton<T extends Enum<T>> implements Automaton<T> {

    private int iterationCounter;

    @Override
    public abstract boolean hasNext();

    @Override
    public Grid2D<AbstractCell<T>> next() {
        this.iterationCounter++;
        return this.doStep();
    }

    abstract Grid2D<AbstractCell<T>> doStep();

    @Override
    public abstract Grid2D<AbstractCell<T>> getGrid();

    @Override
    public Stats<T> getStats() {
        return new StatsImpl<T>(iterationCounter, this.createEnumMap());
    }

    abstract EnumMap<T, Integer> createEnumMap();

}
