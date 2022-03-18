package casim.model.codi;

/** 
 * The states a cell of CoDi Automaton can have.
 */
public enum CellState {
    /**
     * An empty cell.
     */
    BLANK,
    /**
     * The neuron body cells, they collect signals from the surrounding dentritic cell.
     */
    NEURON,
    /**
     * Axonal cells, they distribute data originating from the neuron body.
     */
    AXON,
    /**
     * Dendritic cells, they collect data and eventually pass it to the neuron body.
     */
    DENDRITE,
}
