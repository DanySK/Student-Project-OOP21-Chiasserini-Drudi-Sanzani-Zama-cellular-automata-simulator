package casim.model.gridfactory;

import java.util.function.Supplier;

import casim.model.cell.Cell;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid3D;

/**
 * A Builder for a {@link Grid} of elements of type T.
 * 
 *  @param <T> the type of the finite states of the {@link Automaton}'s {@link Cell}.
 */
public interface AutomatonGridFactory<T> {

    /**
     * Return a new 2D {@link Grid} of elements of type T.
     * 
     * @param width the width of the grid;
     * @param height the height of the grid;
     * @param cellSupplier a supplier of {@link Cell} to generate the grid.
     * @return a new {@link Grid} of type T.
     */
    Grid2D<Cell<T>> create2DGrid(int width, int height, Supplier<Cell<T>> cellSupplier); 

    /**
     * Return a new 3D {@link Grid} of elements of type T.
     * 
     * @param width the width of the grid;
     * @param height the height of the grid;
     * @param depth the depth of the grid;
     * @param cellSupplier a supplier of {@link Cell} to generate the grid.
     * @return a new {@link Grid} of type T.
     */
    Grid3D<Cell<T>> create3DGrid(int width, int height, int depth, Supplier<Cell<T>> cellSupplier);

}
