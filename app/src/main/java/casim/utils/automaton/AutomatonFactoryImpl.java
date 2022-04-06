package casim.utils.automaton;

import java.util.Random;

import casim.model.bryansbrain.BryansBrain;
import casim.model.bryansbrain.BryansBrainConfig;
import casim.model.bryansbrain.BryansBrainCellState;
import casim.model.codi.CoDi;
import casim.utils.grid.Grid2DImpl;
import casim.utils.grid.Grid3DImpl;
import casim.model.codi.cell.attributes.CoDiCellState;
import casim.model.langtonsant.LangtonsAnt;
import casim.model.langtonsant.LangtonsAntCellState;
import casim.model.langtonsant.LangtonsAntConfig;
import casim.model.wator.Wator;
import casim.model.wator.WatorCellState;

/**
 * {@link AutomatonFactory} implementation.
 */
public class AutomatonFactoryImpl implements AutomatonFactory {

    @Override
    public BryansBrain getBryansBrainRandom(final BryansBrainConfig config) {
        final var rand = new Random();
        final var state = new Grid2DImpl<>(config.getRows(), config.getCols(), () -> {
                final var randValue = rand.nextInt();
                return BryansBrainCellState.values()[randValue % BryansBrainCellState.values().length];
        });
        return new BryansBrain(state, config.isWrapped());
    }

    @Override
    public CoDi getCoDi(final int cols, final int rows, final int depth) {
        final var state = new Grid3DImpl<CoDiCellState>(cols, rows, depth, () -> CoDiCellState.BLANK);
        return new CoDi(state);
    }

    @Override
    public LangtonsAnt getLangtonsAnt(final LangtonsAntConfig config) {
        final var state = new Grid2DImpl<>(config.getRows(), config.getCols(), () -> LangtonsAntCellState.OFF);
        return new LangtonsAnt(state, config.getAntNumber(), config.isWrapped());
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
