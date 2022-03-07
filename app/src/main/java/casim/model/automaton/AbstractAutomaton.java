package casim.model.automaton;

import java.util.EnumMap;

import casim.model.cell.AbstractCell;
import casim.model.utils.Stats;
import casim.model.utils.StatsImpl;
import casim.utils.grid.Grid2D;

/**
 * Abstract class for {@link Automaton}.
 *
 * @param <T> the enumeration which contains the finite states of the {@link Automaton}'s {@link Cell}.
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
