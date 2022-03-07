package casim.model;

import java.util.Iterator;

import casim.model.cell.Cell;
import casim.utils.grid.Grid2D;

/**
 *  An interface which describes an {@link Automaton}.
 *
 *  @param <T> the enumeration which contains the finite states of the {@link Automaton}'s {@link Cell}.
 */
public interface Automaton<T extends Enum<T>> extends Iterator<Grid2D<Cell<T>>> {

    /**
     * Get the {@link Grid} describing the current {@link Automaton}'s state.
     * 
     * @return {@link Grid} A {@link Grid} of {@link Cell}.
     */
    Grid2D<Cell<T>> getGrid();

}
