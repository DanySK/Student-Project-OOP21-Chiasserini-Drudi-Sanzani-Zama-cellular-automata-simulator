package casim.model.rule;

import casim.model.cell.interfaces.Cell;
import casim.utils.grid.Grid;

/**
 * The {@link Automaton}'s rule used to update the {@link Cell}'s state.
 * 
 * @param <T> the type of the finite states which the {@link Cell} can assume. 
 */
public interface UpdateRule<T> {

     /**
     * Return the updated {@link Cell}.
     * 
     * @param cell to update.
     * @param grid the {@link Grid} representing the {@link Automaton}.
     * @return the updated {@link Cell}.
     */
    Cell<T> getNextCell(Cell<T> cell, Grid<Cell<T>> grid);

}
