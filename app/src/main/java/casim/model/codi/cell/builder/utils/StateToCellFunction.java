package casim.model.codi.cell.builder.utils;

import java.util.EnumMap;
import java.util.function.Function;

import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.attributes.CellState;
import casim.model.codi.cell.attributes.Direction;
import casim.model.codi.cell.builder.CoDiCellBuilder;
import casim.model.codi.cell.builder.CoDiCellBuilderImpl;
import casim.model.codi.utils.CodiUtils;

/**
 * {@link Function} used to map a {@link CellState} to a new {@link CoDiCell} with that state.
 */
public class StateToCellFunction implements Function<CellState, CoDiCell> {

    private static final int CHROMOSOME_PROBABILITY = 50;

    @Override
    public CoDiCell apply(final CellState state) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final EnumMap<Direction, Integer> neighborsPreviousInput = CodiUtils.newFilledEnumMap(() -> 0);
        final EnumMap<Direction, Boolean> chromosome =
                CodiUtils.newFilledEnumMap(() -> CodiUtils.booleanWithSpecificProbability(CHROMOSOME_PROBABILITY));
        return builder.state(state)
            .activationCounter(0)
            .chromosome(chromosome)
            .neighborsPreviousInput(neighborsPreviousInput)
            .build();
    }

}
