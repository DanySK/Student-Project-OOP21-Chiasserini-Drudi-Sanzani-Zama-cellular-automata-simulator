package casim.model.bryansbrain;

import casim.model.abstraction.cell.AbstractCell;

/**
 * A cell of the Bryan's Brain Automaton.
 */
public class BryansBrainCell extends AbstractCell<BryansBrainCellState> {

    /**
     * Build a new {@link BryansBrainCell}.
     *
     * @param state the cell's state.
     */
    public BryansBrainCell(final BryansBrainCellState state) {
        super(state);
    }

}
