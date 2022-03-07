package casim.model.utils.stats;

import java.util.EnumMap;

/**
 * A {@link Stats} implementation.
 * 
 *  @param <T> the type of the finite states of the {@link casim.model.Automaton}'s {@link casim.model.cell.Cell}.
 */
public class StatsImpl<T extends Enum<T>> implements Stats<T> {

    private final int iterationCounter;
    private final EnumMap<T, Integer> statesMap;

    /**
     * Construct a new {@link StatsImpl}.
     * 
     * @param iterationCounter the counter of the {@link Automaton} iterations.
     * @param statesMap the enumMap which describes the number of cells for each type.
     */
    public StatsImpl(final int iterationCounter, final EnumMap<T, Integer> statesMap) {
        this.iterationCounter = iterationCounter;
        this.statesMap = statesMap;
    }

    @Override
    public int getIteration() {
        return this.iterationCounter;
    }

    @Override
    public EnumMap<T, Integer> getCellStats() {
        return this.statesMap;
    }

}
