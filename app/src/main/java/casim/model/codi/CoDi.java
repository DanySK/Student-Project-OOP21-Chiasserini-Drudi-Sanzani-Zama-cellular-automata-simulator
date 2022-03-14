package casim.model.codi;

import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.model.abstraction.utils.NeighborsFunctions;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid3D;
import casim.utils.grid.Grid3DImpl;

/**
 * Implementation of CoDi {@link casim.model.abstraction.automaton.Automaton}.
 */
public class CoDi extends AbstractAutomaton<CellState, CoDiCell> {

    private Grid3D<CoDiCell> state ;
    private final UpdateRule updateRule;

    /**
     * Constructor of {@link CoDi}.
     * 
     * @param state the grid of {@link CellState} used to initialize the automaton.
     */
    public CoDi(final Grid3D<CellState> state) {
        this.state = state.map(s -> new CoDiCell(s));
        this.updateRule = new UpdateRule(NeighborsFunctions::neighbors3DFunction);
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    protected Grid2D<CoDiCell> doStep() {
        final var newState = new Grid3DImpl<CoDiCell>(this.state.getHeight(), this.state.getWidth(), this.state.getDepth());
        return null;
    }

    @Override
    public Grid2D<CoDiCell> getGrid() {
        // TODO Auto-generated method stub
        return null;
    }

}
