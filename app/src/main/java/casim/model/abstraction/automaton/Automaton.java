package casim.model.abstraction.automaton;

import java.util.Iterator;

import casim.model.abstraction.cell.AbstractCell;
import casim.model.abstraction.utils.stats.Stats;
import casim.utils.grid.Grid2D;

/**
 *  An interface which describes an {@link Automaton}.
 *
 *  @param <T> the enumeration which contains the finite states of the {@link Automaton}'s {@link Cell}.
 */
public interface Automaton<T extends Enum<T>> extends Iterator<Grid2D<AbstractCell<T>>> {

    /**
     * Get the {@link Grid} describing the current {@link Automaton}'s state.
     * 
     * @return {@link Grid} A {@link Grid} of {@link Cell}.
     */
    Grid2D<AbstractCell<T>> getGrid();

    /**
     * Get the {@link Stats} about the {@link Automaton}.
     * 
     * @return a {@link Stats} object containing all the stats data.
     */
    Stats<T> getStats();

}
