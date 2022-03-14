package casim.model.langtonsant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
        // TODO complete
        return null;
    }

    @Override
    public Grid2D<LangtonsAntCell> getGrid() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
