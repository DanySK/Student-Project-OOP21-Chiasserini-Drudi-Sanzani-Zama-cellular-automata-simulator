package casim.model.automaton;

import casim.model.cell.AbstractCell;
import casim.model.utils.Stats;
import casim.utils.grid.Grid2D;

/**
 * Abstract class for {@link Automaton}.
 *
 * @param <T> the enumeration which contains the finite states of the {@link Automaton}'s {@link Cell}.
 */
public class AbstractAutomaton<T extends Enum<T>> implements Automaton<T> {

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Grid2D<AbstractCell<T>> next() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Grid2D<AbstractCell<T>> getGrid() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Stats<T> getStats() {
        // TODO Auto-generated method stub
        return null;
    }

}
