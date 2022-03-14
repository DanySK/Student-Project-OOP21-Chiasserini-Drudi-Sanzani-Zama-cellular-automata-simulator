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
     * Prerequisite for the neuron growth.
     */
    NEURONSEED,
    /**
     * The neuron body cells, they collect signals from the surrounding dentritic cell.
     */
    NEURON,
    /**
     * Axonal cells, they distribute data originating from the neuron body.
     */
    AXON,
    /**
     * 
     */
    AXON_SIGNAL,
    /**
     * Dentritic cells, they collect data and eventually pass it to the neuron body.
     */
    DENTRITE,
    /**
     * 
     */
    DENTRITE_SIGNAL;
}
