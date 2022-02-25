package casim.model.gridfactory;

import java.util.function.Supplier;

import casim.model.cell.Cell;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid2DImpl;
import casim.utils.grid.Grid3D;
import casim.utils.grid.Grid3DImpl;

/**
 * Implementation of {@link AutomatonGridFactory}.
 * 
 *  @param <T> the type of the finite states of the {@link Automaton}'s {@link Cell}.
 */
public class AutomatonGridFactoryImpl<T> implements AutomatonGridFactory<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Grid2D<Cell<T>> create2DGrid(final int width, final int height, final Supplier<Cell<T>> cellSupplier) {
        return new Grid2DImpl<>(width, height, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Grid3D<Cell<T>> create3DGrid(final int width, final int height, final int depth, final Supplier<Cell<T>> cellSupplier) {
        return new Grid3DImpl<>(width, height, depth, null);
    }

}
