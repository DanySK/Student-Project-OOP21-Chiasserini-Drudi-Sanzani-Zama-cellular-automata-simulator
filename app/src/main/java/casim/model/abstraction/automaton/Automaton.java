package casim.model.abstraction.automaton;

import java.util.Iterator;

import casim.model.abstraction.cell.AbstractCell;
import casim.model.abstraction.utils.stats.Stats;
import casim.utils.grid.Grid2D;

/**
 *  An interface which describes an {@link Automaton}.
 *
 *  @param <T> the enumeration which contains the finite states of the {@link Automaton}'s {@link casim.model.abstraction.cell.Cell}.
 */
public interface Automaton<S, T extends AbstractCell<?>> extends Iterator<Grid2D<T>> {

    /**
     * Get the {@link Grid2D} describing the current {@link Automaton}'s state.
     * 
     * @return {@link Grid2D} A {@link Grid2D} of {@link casim.model.abstraction.cell.Cell}.
     */
    Grid2D<T> getGrid();

    /**
     * Get the {@link Stats} about the {@link Automaton}.
     * 
     * @return a {@link Stats} object containing all the stats data.
     */
    Stats<S> getStats();

}
