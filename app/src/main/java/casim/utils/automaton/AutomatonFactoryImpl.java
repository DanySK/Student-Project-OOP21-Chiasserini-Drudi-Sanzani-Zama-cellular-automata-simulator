package casim.utils.automaton;

import casim.model.bryansbrain.BryansBrain;
import casim.model.codi.CoDi;
import casim.utils.grid.Grid2DImpl;
import casim.utils.grid.Grid3DImpl;
import casim.model.codi.cell.attributes.CellState;
import casim.model.langtonsant.LangtonsAnt;
import casim.model.langtonsant.LangtonsAntCellState;

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

    @Override
    public LangtonsAnt getLangtonsAnt(final int cols, final int rows, final int antNumber, final boolean wrapping) {
        final var state = new Grid2DImpl<>(rows, cols, () -> LangtonsAntCellState.OFF);
        return new LangtonsAnt(state, antNumber, wrapping);
    }

}
