package casim.model;

import casim.utils.grid.Grid;

/**
 * A Builder for a {@link Grid} of elements of type T.
 * 
 * @param <T> type of the elements contained in the {@link Grid}.
 */
public interface GridBuilder<T> {

    /**
     * Return a new {@link Grid} of elements of type T.
     * 
     * @return a new {@link Grid} of type T.
     */
    Grid<T> getGrid();

}
