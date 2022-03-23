package casim.model.codi.cell.builder.utils;

import java.util.EnumMap;
import java.util.function.Function;

import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.attributes.CellState;
import casim.model.codi.cell.attributes.Direction;
import casim.model.codi.cell.builder.CoDiCellBuilder;
import casim.model.codi.cell.builder.CoDiCellBuilderImpl;
import casim.model.codi.rule.RulesUtils;

/**
 * {@link Function} used to map a {@link CellState} to a new {@link CoDiCell} with that state.
 */
public class StateToCellFunction implements Function<CellState, CoDiCell> {

    @Override
    public CoDiCell apply(final CellState state) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final EnumMap<Direction, Integer> neighborsPreviousInput = RulesUtils.newFilledEnumMap(() -> 0);
        final EnumMap<Direction, Boolean> chromosome = RulesUtils.newFilledEnumMap(() -> RulesUtils.rand50());
        builder.state(state);
        builder.activationCounter(0);
        builder.chromosome(chromosome);
        builder.neighborsPreviousInput(neighborsPreviousInput);
        return builder.build();
    }

}
