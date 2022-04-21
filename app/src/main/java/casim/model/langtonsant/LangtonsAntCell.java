package casim.model.langtonsant;

import casim.model.abstraction.cell.AbstractCell;

/**
 * A Cell of the LangtonsAnt automaton.
 */
public class LangtonsAntCell extends AbstractCell<LangtonsAntCellState> {

    /**
     * Constructs a new {@link LangtonsAntCell} with a
     * {@link LangtonsAntCellState} from argument.
     * 
     * @param state the {@link LangtonsAntCellState} of the {@link LangtonsAntCell}.
     */
    public LangtonsAntCell(final LangtonsAntCellState state) {
        super(state);
    }
}
