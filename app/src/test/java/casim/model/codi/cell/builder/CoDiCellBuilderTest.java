package casim.model.codi.cell.builder;

import java.util.EnumMap;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.attributes.CoDiCellState;
import casim.model.codi.cell.attributes.Direction;
import casim.model.codi.utils.CodiUtils;

/**
 * Test class for {@link CoDiCellBuilder}.
 */
class CoDiCellBuilderTest {

    private static final int CHROMOSOME_PROBABILITY = 50;
    private static final CoDiCellState STATE = CoDiCellState.BLANK;

    /**
     * Test for {@link CoDiCellBuilder#build()}.
     */
    @Test
    void testBuild() {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        Assert.assertThrows(IllegalStateException.class, () -> builder.build());
        final EnumMap<Direction, Integer> neighborsPreviousInput = CodiUtils.newFilledEnumMap(() -> 0);
        final EnumMap<Direction, Boolean> chromosome =
                CodiUtils.newFilledEnumMap(() -> CodiUtils.booleanWithSpecificProbability(CHROMOSOME_PROBABILITY));
        builder.state(STATE)
               .activationCounter(0)
               .chromosome(chromosome)
               .neighborsPreviousInput(neighborsPreviousInput);
        final CoDiCell cell = builder.build();
        Assert.assertTrue(cell.getGate().isEmpty());
        Assert.assertThrows(IllegalStateException.class, () -> builder.build());
    }
}
