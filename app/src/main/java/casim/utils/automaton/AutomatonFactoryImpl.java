package casim.utils.automaton;

import java.util.Random;

import casim.model.Automata;
import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.model.abstraction.cell.AbstractCell;
import casim.model.bryansbrain.BryansBrain;
import casim.model.bryansbrain.BryansBrainCellState;
import casim.model.codi.CoDi;
import casim.model.codi.CoDiConfig;
import casim.utils.automaton.config.BaseConfig;
import casim.utils.automaton.config.WrappingConfig;
import casim.utils.grid.Grid2DImpl;
import casim.utils.grid.Grid3DImpl;
import casim.model.codi.cell.attributes.CoDiCellState;
import casim.model.gameoflife.GameOfLife;
import casim.model.langtonsant.LangtonsAnt;
import casim.model.langtonsant.LangtonsAntCellState;
import casim.model.langtonsant.LangtonsAntConfig;
import casim.model.rule110.Rule110;
import casim.model.rule110.Rule110CellState;
import casim.model.wator.Wator;
import casim.model.wator.WatorCellState;
import casim.model.gameoflife.GameOfLifeState;

/**
 * {@link AutomatonFactory} implementation.
 */
public class AutomatonFactoryImpl implements AutomatonFactory {
    private static final String UNKNOWN_AUTOMATON = "Unknown automaton.";
    private static final int MAX_CHANCE = 101;
    private static final int PREY_CHANCE = 2;
    private static final int PREDATOR_CHANCE = 3;

    @Override
    public BryansBrain getBryansBrainRandom(final WrappingConfig config) {
        final var rand = new Random();
        final var state = new Grid2DImpl<>(config.getRows(), config.getCols(), () -> {
                final var randValue = rand.nextInt(BryansBrainCellState.values().length);
                return BryansBrainCellState.values()[randValue];
        });
        return new BryansBrain(state, config.isWrapped());
    }

    @Override
    public CoDi getCoDi(final CoDiConfig config) {
        final var state = new Grid3DImpl<CoDiCellState>(config.getCols(), config.getRows(), config.getDepth(),
                () -> CoDiCellState.BLANK);
        return new CoDi(state);
    }

    @Override
    public LangtonsAnt getLangtonsAnt(final LangtonsAntConfig config) {
        final var state = new Grid2DImpl<>(config.getRows(), config.getCols(), () -> LangtonsAntCellState.OFF);
        return new LangtonsAnt(state, config.getAntNumber(), config.isWrapped());
    }

    @Override
    public Wator getWator(final BaseConfig config) {
        final var rng = new Random();
        final var state = new Grid2DImpl<>(config.getRows(), config.getCols(), () -> {
            final var val = rng.nextInt(MAX_CHANCE);
            if (val <= PREY_CHANCE) {
                return WatorCellState.PREY;
            } else if (val <= PREDATOR_CHANCE + PREY_CHANCE) {
                return WatorCellState.PREDATOR;
            }
            return WatorCellState.DEAD;
        });
        return new Wator(state);
    }

    @Override
    public Rule110 getRule110(BaseConfig config) {
        return new Rule110(Math.min(config.getRows(), config.getCols()));
    }

    @Override
    public GameOfLife getGameOfLife(WrappingConfig config) {
        final var rng = new Random();
        final var state = new Grid2DImpl<>(config.getRows(), config.getCols(), () -> {
            final var val = rng.nextInt(GameOfLifeState.values().length);
            return GameOfLifeState.values()[val];
        });
        return new GameOfLife(state);
    }

    @Override
    public <S, T extends AbstractCell<S>, C extends BaseConfig, O extends AbstractAutomaton<S, T>> O getAutomatonFromAutomataEnum(
            Automata automata, C config) {
        switch (automata) {
            case CODI:
                return (O) this.getCoDi((CoDiConfig) config);
            case RULE110:
                return (O) this.getRule110(config);
            case WATOR:  
                return (O) this.getWator(config);
            case BRYANS_BRAIN:
                return (O) this.getBryansBrainRandom((WrappingConfig) config);
            case GAME_OF_LIFE:
                return (O) this.getGameOfLife((WrappingConfig) config);
            case LANGTONS_ANT:
                return (O) this.getLangtonsAnt((LangtonsAntConfig) config);
            default:
                throw new IllegalArgumentException(UNKNOWN_AUTOMATON);
        }
    }
}
