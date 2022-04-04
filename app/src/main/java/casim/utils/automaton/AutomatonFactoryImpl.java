package casim.utils.automaton;

import casim.model.bryansbrain.BryansBrain;
import casim.model.codi.CoDi;
import casim.utils.grid.Grid3DImpl;
import casim.model.codi.cell.attributes.CellState;

/**
 * {@link AutomatonFactory} implementation.
 */
public class AutomatonFactoryImpl implements AutomatonFactory {

    @Override
    public BryansBrain getBryansBrainRandom(final int cols, final int rows) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CoDi getCoDi(final int cols, final int rows, final int depth) {
        final var state = new Grid3DImpl<CellState>(cols, rows, depth, () -> CellState.BLANK);
        return new CoDi(state);
    }

}
