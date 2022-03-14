package casim.model.abstraction.gridfactory;

import java.util.function.Supplier;

import casim.model.abstraction.cell.AbstractCell;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid2DImpl;
import casim.utils.grid.Grid3D;
import casim.utils.grid.Grid3DImpl;

/**
 * Implementation of {@link AutomatonGridFactory}.
 * 
 *  @param <T> the enumeration which contains the finite states of the {@link casim.model.abstraction.automaton.Automaton}'s {@link Cell}.
 */
public class AutomatonGridFactoryImpl<T extends AbstractCell<?>> implements AutomatonGridFactory<T> {

    @Override
    public Grid2D<T> create2DGrid(final int width, final int height, final Supplier<T> cellSupplier) {
        return new Grid2DImpl<>(width, height, cellSupplier);
    }

    @Override
    public Grid3D<T> create3DGrid(final int width, final int height, final int depth, final  Supplier<T> cellSupplier) {
        return new Grid3DImpl<>(width, height, depth, cellSupplier);
    }

}
