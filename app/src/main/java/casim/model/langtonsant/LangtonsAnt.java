package casim.model.langtonsant;

import java.util.ArrayList;
import java.util.List;
import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid2DImpl;

public class LangtonsAnt extends AbstractAutomaton<CellState, LangtonsAntCell>{

    private final List<Ant> ants = new ArrayList<Ant>();
    private Grid2D<LangtonsAntCell> state;

    public LangtonsAnt(final Grid2D<CellState> state) {
        this.state = state.map(x -> new LangtonsAntCell(x));
    }

    @Override
    public boolean hasNext() {
        return !this.ants.isEmpty();
    }

    @Override
    protected Grid2D<LangtonsAntCell> doStep() {
        final var newState = new Grid2DImpl<LangtonsAntCell>(this.state.getHeight(), this.state.getWidth());

        this.ants.stream()
            .forEach(x -> this.antStep(x, newState));
        return newState;
    }

    private void antStep(final Ant ant, final Grid2DImpl<LangtonsAntCell> state) {
        ant.setDirection(Direction.turn(ant.getDirection(), state.get(ant.getPosition()).getState()));
        // Change state of the cell the ant is on
        state.set(ant.getPosition(),
            new LangtonsAntCell(state.get(ant.getPosition()).getState() == CellState.OFF ? CellState.ON : CellState.OFF));
        // TODO move ant towards facing direction  
    }

    @Override
    public Grid2D<LangtonsAntCell> getGrid() {
        return this.state;
        // TODO return unmodifiable copy
    }
    
}
