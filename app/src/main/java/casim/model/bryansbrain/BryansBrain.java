package casim.model.bryansbrain;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.model.abstraction.utils.NeighborsFunctions;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid2DImpl;
import casim.utils.grid.WrappingGrid;
import casim.utils.range.Ranges;

public class BryansBrain extends AbstractAutomaton<BryansBrainCellState, BryansBrainCell> {
    private final boolean wrapping;

    private Grid2D<BryansBrainCell> state;
    private final BryansBrainUpdateRule updateRule
        = new BryansBrainUpdateRule(NeighborsFunctions::mooreNeighborsFunction);

    public BryansBrain(final Grid2D<BryansBrainCellState> state, final boolean wrapping) {
        this.wrapping = wrapping;
        this.state = this.wrapping 
            ? new WrappingGrid<>(state.map(s -> new BryansBrainCell(s)))
            : state.map(s -> new BryansBrainCell(s));
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    protected Grid2D<BryansBrainCell> doStep() {
        Grid2D<BryansBrainCell> newState = new Grid2DImpl<BryansBrainCell>(this.state.getHeight(), this.state.getWidth());
        if (this.wrapping) {
            newState = new WrappingGrid<>(newState);
        }
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
