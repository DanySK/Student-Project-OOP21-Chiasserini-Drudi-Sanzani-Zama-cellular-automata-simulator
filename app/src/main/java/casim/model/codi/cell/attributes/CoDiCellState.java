package casim.model.codi.cell.attributes;

/** 
 * The states a cell of CoDi Automaton can have.
 */
public enum CoDiCellState {
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
     * An activated axon.
     */
    ACTIVATED_AXON,
    /**
     * Dendritic cells, they collect data and eventually pass it to the neuron body.
     */
    DENDRITE,
    /**
     * An activated dendrite. 
     */
    ACTIVATED_DENDRITE;
}
