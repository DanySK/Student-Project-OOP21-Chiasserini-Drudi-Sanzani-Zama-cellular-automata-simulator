package casim.model.langtonsant;

import casim.model.abstraction.cell.AbstractCell;

/**
 * A Cell of the {@link casim.model.langtonsant.LangtonsAnt} automaton.
 */
public class LangtonsAntCell extends AbstractCell<CellState> {

    /**
     * Constructs a new {@link LangtonsAntCell} with a
     * {@link CellState} from argument.
     * 
     * @param state the {@link CellState} of the {@link LantonsAntCell}.
     */
    public LangtonsAntCell(final CellState state) {
        super(state);
    }
}
