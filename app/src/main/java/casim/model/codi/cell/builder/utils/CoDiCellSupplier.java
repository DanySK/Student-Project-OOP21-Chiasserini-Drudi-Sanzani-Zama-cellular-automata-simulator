package casim.model.codi.cell.builder.utils;

import java.util.EnumMap;
import java.util.function.Supplier;

import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.attributes.CellState;
import casim.model.codi.cell.attributes.Direction;
import casim.model.codi.cell.builder.CoDiCellBuilder;
import casim.model.codi.cell.builder.CoDiCellBuilderImpl;
import casim.model.codi.rule.RulesUtils;

/**
 * {@link CoDiCell} supplier  used to fill the initial grid.
 */
public class CoDiCellSupplier implements Supplier<CoDiCell> {

    @Override
    public CoDiCell get() {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final EnumMap<Direction, Integer> neighborsPreviousInput = RulesUtils.newFilledEnumMap(0);
        final EnumMap<Direction, Boolean> chromosome = new EnumMap<>(Direction.class);
        for (final var d: Direction.values()) {
            chromosome.put(d, RulesUtils.rand50());
        }
        builder.state(CellState.BLANK);
        builder.activationCounter(0);
        builder.chromosome(chromosome);
        builder.neighborsPreviousInput(neighborsPreviousInput);
        return builder.build();
    }

}
