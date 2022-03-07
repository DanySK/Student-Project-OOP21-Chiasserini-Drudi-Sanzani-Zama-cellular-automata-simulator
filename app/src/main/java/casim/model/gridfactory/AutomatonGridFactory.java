package casim.model.gridfactory;

import java.util.function.Supplier;

import casim.model.cell.Cell;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid3D;

/**
 * A factory for {@link casim.model.Automaton}'s {@link casim.utils.grid.Grid}.
 * 
 *  @param <T> the enumeration which contains the finite states of the {@link casim.model.Automaton}'s {@link Cell}.
 */
public interface AutomatonGridFactory<T extends Enum<T>> {

    /**
     * Return a new {@link Grid2D} of elements of type T.
     * 
     * @param width the width of the grid;
     * @param height the height of the grid;
     * @param cellSupplier a supplier of {@link Cell} to generate the grid.
     * @return a new {@link Grid2D} of {@link Cell} of state T.
     */
    Grid2D<Cell<T>> create2DGrid(int width, int height, Supplier<Cell<T>> cellSupplier); 

    /**
     * Return a new {@link Grid3D} of elements of type T.
     * 
     * @param width the width of the grid;
     * @param height the height of the grid;
     * @param depth the depth of the grid;
     * @param cellSupplier a supplier of {@link Cell} to generate the grid.
     * @return a new {@link Grid3D} of {@link Cell} of state T.
     */
    Grid3D<Cell<T>> create3DGrid(int width, int height, int depth, Supplier<Cell<T>> cellSupplier);

}
