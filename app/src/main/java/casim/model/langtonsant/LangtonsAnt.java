package casim.model.langtonsant;

import java.util.ArrayList;
import java.util.List;
import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.utils.PlayableAutomaton;
import casim.utils.grid.Grid2D;

@PlayableAutomaton(AutomatonName = "Langton's Ant")
public class LangtonsAnt extends AbstractAutomaton<CellState, LangtonsAntCell>{

    private final List<Ant> ants;
    private final Grid2D<LangtonsAntCell> state;

    public LangtonsAnt(final Grid2D<CellState> state, final List<Ant> ants) {
        this.state = state.map(x -> new LangtonsAntCell(x));
        this.ants = ants;
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
        ant.turn(this.state.get(ant.getPosition()).getState());
        this.state.set(ant.getPosition(), new LangtonsAntCell(
                this.state.get(ant.getPosition()).getState() == CellState.OFF ? CellState.ON : CellState.OFF));
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
