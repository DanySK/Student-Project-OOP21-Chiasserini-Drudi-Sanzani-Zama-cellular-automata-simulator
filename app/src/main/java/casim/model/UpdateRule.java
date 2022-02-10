package casim.model;

import java.util.Iterator;

/**
 * The {@link Automaton}'s rule used to update the {@link Cell}'s state.
 */
public interface UpdateRule {

     /**
     * Return the updated {@link CellAttributes} for the {@link Cell} take as parameter.
     * 
     * @param cell to update.
     * @param neighbors iterator of the cell's neighbors.
     * @return the updated cell's {@link CellAttributes}.
     */
    CellAttributes getNext(Cell cell, Iterator<Cell> neighbors);

}
