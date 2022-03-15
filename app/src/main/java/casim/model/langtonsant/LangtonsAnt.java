package casim.model.langtonsant;

import java.util.ArrayList;
import java.util.List;
import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.utils.PlayableAutomaton;
import casim.utils.grid.Grid2D;

@PlayableAutomaton(AutomatonName = "Langton's Ant")
public class LangtonsAnt extends AbstractAutomaton<CellState, LangtonsAntCell>{

    private final List<Ant> ants = new ArrayList<Ant>();
    private final Grid2D<LangtonsAntCell> state;

    public LangtonsAnt(final Grid2D<CellState> state) {
        this.state = state.map(x -> new LangtonsAntCell(x));
    }

    @Override
    public boolean hasNext() {
        return !this.ants.isEmpty();
    }

    @Override
    protected Grid2D<LangtonsAntCell> doStep() {
        this.ants.stream()
            .forEach(x -> this.antStep(x));
        this.removeAnts();
        return this.state;
    }

    private void antStep(final Ant ant) {
        // Change the direction of the ant based on the state of its cell
        // ant.setDirection(Direction.turn(ant.getDirection(), state.get(ant.getPosition()).getState()));
        ant.turn(state.get(ant.getPosition()).getState());
        // Change state of the cell the ant is on
        this.state.set(ant.getPosition(),
                new LangtonsAntCell(this.state.get(ant.getPosition()).getState() == CellState.OFF ? CellState.ON : CellState.OFF));
        // Move the ant in the direction it is facing
        ant.move();
    }

    private void removeAnts() {
        final List<Ant> toBeRemoved = new ArrayList<Ant>();
        this.ants.stream()
            .filter(x -> this.state.isCoordValid(x.getPosition()))
            .forEach(x -> toBeRemoved.add(x));;
        toBeRemoved.stream()
            .forEach(x -> this.ants.remove(x));
    }

    @Override
    public Grid2D<LangtonsAntCell> getGrid() {
        return this.state;
        // TODO return unmodifiable copy
    }
    
}
