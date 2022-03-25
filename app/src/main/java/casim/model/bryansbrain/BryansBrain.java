package casim.model.bryansbrain;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.model.abstraction.utils.NeighborsFunctions;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid2DImpl;
import casim.utils.range.Ranges;

public class BryansBrain extends AbstractAutomaton<CellState, BryansBrainCell> {

    private Grid2D<BryansBrainCell> state;
    private final UpdateRule updateRule
        = new UpdateRule(NeighborsFunctions::mooreNeighborsFunction);

    public BryansBrain(final Grid2D<CellState> state) {
        this.state = state.map(s -> new BryansBrainCell(s));
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    protected Grid2D<BryansBrainCell> doStep() {
        final var newState = new Grid2DImpl<BryansBrainCell>(this.state.getHeight(), this.state.getWidth());

        for (final var x : Ranges.of(0, this.state.getHeight())) {
            for (final var y : Ranges.of(0, this.state.getWidth())) {
                final var coord = CoordinatesUtil.of(x, y);
                newState.set((coord), this.updateRule.getNextCell(Pair.of(coord, this.state.get(coord)), this.state));
            }
        }

        this.state = newState;
        return this.state;
    }

    @Override
    public Grid2D<BryansBrainCell> getGrid() {
        // TODO return unmodifiable copy
        return this.state;
    }
}
