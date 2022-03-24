package casim.model.codi.cell.builder.utils;

import java.util.EnumMap;
import java.util.function.Supplier;

import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.attributes.CellState;
import casim.model.codi.cell.attributes.Direction;
import casim.model.codi.cell.builder.CoDiCellBuilder;
import casim.model.codi.cell.builder.CoDiCellBuilderImpl;
import casim.model.codi.utils.CodiUtils;

/**
 * {@link CoDiCell} supplier  used to fill the initial grid with blank cells.
 */
public class CoDiCellSupplier implements Supplier<CoDiCell> {

    private static final int CHROMOSOME_PROBABILITY = 50;

    @Override
    public CoDiCell get() {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final EnumMap<Direction, Integer> neighborsPreviousInput = CodiUtils.newFilledEnumMap(() -> 0);
        final EnumMap<Direction, Boolean> chromosome =
                CodiUtils.newFilledEnumMap(() -> CodiUtils.booleanWithSpecificProbability(CHROMOSOME_PROBABILITY));
        builder.state(CellState.BLANK);
        builder.activationCounter(0);
        builder.chromosome(chromosome);
        builder.neighborsPreviousInput(neighborsPreviousInput);
        return builder.build();
    }

}
