package casim.model;

import java.util.Iterator;

/**
 * The {@link Automaton}'s rule used to update the {@link Cell}'s state.
 * 
 * @param <T> the type of the finite states which the {@link Cell} can assume. 
 */
public interface UpdateRule<T> {

     /**
     * Return the updated {@link CellAttributes} for the {@link Cell} take as parameter.
     * 
     * @param cell to update.
     * @param neighbors iterator of the cell's neighbors.
     * @return the updated cell's {@link CellAttributes}.
     */
    CellAttributes<T> getNext(Cell<T> cell, Iterator<Cell<T>> neighbors);

}
