package casim.model.rule;

import casim.model.cell.interfaces.Cell;

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
     * @return the updated {@link Cell}.
     */
    Cell<T> getNextCell(Cell<T> cell);

}
