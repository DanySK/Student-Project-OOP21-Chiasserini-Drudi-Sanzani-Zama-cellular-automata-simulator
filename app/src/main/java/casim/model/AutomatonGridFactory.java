package casim.model;

import casim.model.cell.interfaces.Cell;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid3D;

/**
 * A Builder for a {@link Grid} of elements of type T.
 * 
 * @param <T> type of the elements contained in the {@link Grid}.
 */
public interface AutomatonGridFactory<T> {

    /**
     * Return a new 2D {@link Grid} of elements of type T.
     * 
     * @param width the width of the grid
     * @param height the height of the grid
     * @return a new {@link Grid} of type T.
     */
    Grid2D<Cell<T>> create2DGrid(int width, int height); 

    /**
     * Return a new 3D {@link Grid} of elements of type T.
     * 
     * @param width the width of the grid
     * @param height the height of the grid
     * @param depth the depth of the grid
     * @return a new {@link Grid} of type T.
     */
    Grid3D<Cell<T>> create3DGrid(int width, int height, int depth);

}
