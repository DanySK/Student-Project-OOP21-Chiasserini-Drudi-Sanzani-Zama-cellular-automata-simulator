package casim.model.wator;

import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.model.abstraction.utils.NeighborsFunctions;
import casim.utils.PlayableAutomaton;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid2DImpl;
import casim.utils.range.Ranges;

@PlayableAutomaton(AutomatonName = "Predators and Preys")
public class Wator extends AbstractAutomaton<CellState, WatorCell>{

    private static final int MAX_HEALTH = 10;
    private static final int INIT_PREDATOR_HEALTH = 5;
    private static final int INIT_PREY_HEALTH = 5;
    private static final int DEAD_HEALTH = 0;

    private Grid2D<WatorCell> state;
    private final UpdateRule updateRule = new UpdateRule(NeighborsFunctions::neighbors2DFunction);

    public Wator(final Grid2D<CellState> state) {
        this.state = state.map(x -> {
            final var rand = new Random();
            final var randInt = rand.nextInt(1000);
            if (randInt < 50) {
                return new WatorCell(CellState.PREDATOR, INIT_PREDATOR_HEALTH, MAX_HEALTH);
            } else if (randInt < 100) {
                return new WatorCell(CellState.PREY, INIT_PREY_HEALTH, MAX_HEALTH);
            } else {
                return new WatorCell(CellState.DEAD, DEAD_HEALTH, MAX_HEALTH);
            }
        });
    }

    @Override
    public boolean hasNext() {
        // return this.state.stream().anyMatch(x -> !x.getState().equals(CellState.DEAD));
        return true;
    }

    @Override
    protected Grid2D<WatorCell> doStep() {
        final var newState = new Grid2DImpl<WatorCell>(this.state.getHeight(), this.state.getWidth());
        for (final var x : Ranges.of(0, this.state.getHeight())) {
            for (final var y : Ranges.of(0, this.state.getWidth())) {
                final var coord = CoordinatesUtil.of(x, y);
                newState.set(coord, this.updateRule.getNextCell(Pair.of(coord, this.state.get(coord)), this.state));
            }
        }
        this.state = newState;
        return this.state;
    }

    @Override
    public Grid2D<WatorCell> getGrid() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
