package casim.model.abstraction.gridfactory;

import java.util.function.Supplier;

import casim.model.abstraction.cell.AbstractCell;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid3D;

/**
 * A factory for {@link casim.model.abstraction.automaton.Automaton}'s {@link casim.utils.grid.Grid}.
 * 
 *  @param <T> the enumeration which contains the finite states of the {@link casim.model.abstraction.automaton.Automaton}'s {@link Cell}.
 */
public interface AutomatonGridFactory<T extends AbstractCell<?>> {

    /**
     * Return a new {@link Grid2D} of a {@link AbstractCell}'s implementation.
     * 
     * @param width the width of the grid;
     * @param height the height of the grid;
     * @param cellSupplier a supplier of an {@link AbstractCell} implementation used to fill the grid.
     * @return a new {@link Grid2D} of an {@link AbstractCell} implementation.
     */
    Grid2D<T> create2DGrid(int width, int height, Supplier<T> cellSupplier); 

    /**
     * Return a new {@link Grid3D} of a {@link AbstractCell} implementation.
     * 
     * @param width the width of the grid;
     * @param height the height of the grid;
     * @param depth the depth of the grid;
     * @param cellSupplier a supplier of an {@link AbstractCell} implementation used to fill the grid.
     * @return a new {@link Grid3D} of an {@link AbstractCell} implementation.
     */
    Grid3D<T> create3DGrid(int width, int height, int depth, Supplier<T> cellSupplier);

}
