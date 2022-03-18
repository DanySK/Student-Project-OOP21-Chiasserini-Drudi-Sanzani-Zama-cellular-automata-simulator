package casim.model.codi;

import java.util.EnumMap;
import java.util.function.Supplier;

import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.builder.CoDiCellBuilder;
import casim.model.codi.cell.builder.CoDiCellBuilderImpl;

/**
 * Random {@link CoDiCell} supplier used to fill the initial grid.
 */
public class CoDiCellSupplier implements Supplier<CoDiCell> {

    @Override
    public CoDiCell get() {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        builder.state(CellState.BLANK);
        builder.activationCounter(0);
        builder.neighborsPreviousInput(this.initialEnumMap());
        return builder.build();
    }

    private EnumMap<Direction, Integer> initialEnumMap() {
        final EnumMap<Direction, Integer> neighborsPreviousInput = new EnumMap<>(Direction.class);
        for (final var d: Direction.values()) {
            neighborsPreviousInput.put(d, 0);
        }
        return neighborsPreviousInput;
    }

}
