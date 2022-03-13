package casim.model.abstraction.automaton;

import java.util.Map;
import java.util.stream.Collectors;

import casim.model.abstraction.cell.AbstractCell;
import casim.model.abstraction.utils.stats.Stats;
import casim.model.abstraction.utils.stats.StatsImpl;
import casim.utils.grid.Grid2D;

/**
 * Abstract class for {@link Automaton}.
 *
 * @param <T> the enumeration which contains the finite states of the {@link Automaton}'s {@link casim.model.abstraction.cell.Cell}.
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

    protected abstract Grid2D<T> doStep();

    @Override
    public abstract Grid2D<T> getGrid();

    @Override
    public Stats<S> getStats() {
        return new StatsImpl<S>(iterationCounter, this.createEnumMap());
    }

    protected Map<S, Integer> createEnumMap() {
        return this.getGrid().stream()
            .collect(Collectors.groupingBy(AbstractCell::getState, Collectors.counting()))
            .entrySet().stream()
            .map(e -> Map.entry(e.getKey(), e.getValue().intValue()))
            .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
