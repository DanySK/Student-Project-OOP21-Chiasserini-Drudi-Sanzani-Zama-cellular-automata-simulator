package casim.utils.automaton;

import java.util.Random;

import casim.model.bryansbrain.BryansBrain;
import casim.model.codi.CoDi;
import casim.utils.grid.Grid2DImpl;
import casim.utils.grid.Grid3DImpl;
import casim.model.codi.cell.attributes.CellState;
import casim.model.langtonsant.LangtonsAnt;
import casim.model.langtonsant.LangtonsAntCellState;
import casim.model.wator.Wator;
import casim.model.wator.WatorCellState;

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

    @Override
    public Wator getWator(int cols, int rows) {
        final var rng = new Random();
        final var state = new Grid2DImpl<>(rows, cols, () -> {
            final var val = rng.nextInt(WatorCellState.values().length);
            return WatorCellState.values()[val];
        });
        return new Wator(state);
    }

}
