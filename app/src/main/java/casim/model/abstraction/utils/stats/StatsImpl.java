package casim.model.abstraction.utils.stats;

import java.util.Map;

/**
 * A {@link Stats} implementation.
 * 
 *  @param <T> the type of the finite states of the {@link casim.model.Automaton}'s {@link casim.model.abstraction.cell.Cell}.
 */
public class StatsImpl<T> implements Stats<T> {

    private final int iterationCounter;
    private final Map<T, Integer> statesMap;

    /**
     * Construct a new {@link StatsImpl}.
     * 
     * @param iterationCounter the counter of the {@link Automaton} iterations.
     * @param statesMap the enumMap which describes the number of cells for each type.
     */
    public StatsImpl(final int iterationCounter, final Map<T, Integer> statesMap) {
        this.iterationCounter = iterationCounter;
        this.statesMap = statesMap;
    }

    @Override
    public int getIteration() {
        return this.iterationCounter;
    }

    @Override
    public Map<T, Integer> getCellStats() {
        return this.statesMap;
    }

}
