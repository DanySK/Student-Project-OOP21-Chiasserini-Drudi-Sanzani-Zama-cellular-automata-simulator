package casim.model.abstraction.utils.stats;

import java.util.Map;

import casim.model.codi.cell.attributes.CoDiCellState;

/**
 * Stats implementation for CoDi automaton.
 */
public class CoDiStatsImpl extends StatsImpl<CoDiCellState> {

    private final int outputLayer;

    /**
     * Construct a new {@link CoDiStatsImpl}.
     * 
     * @param iterationCounter the counter of the automaton iterations.
     * @param statesMap the map which describes the number of cells for each type.
     * @param outputlayer the current output layer.
     */
    public CoDiStatsImpl(final int iterationCounter, final Map<CoDiCellState, Integer> statesMap, final int outputlayer) {
        super(iterationCounter, statesMap);
        this.outputLayer = outputlayer;
    }

    /**
     * Return the current output layer.
     * 
     * @return the current output layer.
     */
    public int getOutputLayer() {
        return this.outputLayer;
    }

    @Override
    public String toString() {
        return "CoDiStatsImpl [outputLayer=" + outputLayer + ", getIteration()=" + getIteration() + ", getCellStats()="
                + getCellStats() + "]";
    }

}
