package casim.model.codi.cell.builder.utils;

import java.util.EnumMap;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.attributes.CoDiCellState;
import casim.model.codi.cell.attributes.Direction;
import casim.model.codi.utils.CodiUtils;

/**
 * test class for {@link StateToCellFunction}.
 */
class StateToCellFunctionTest {

    private static final CoDiCellState STATE = CoDiCellState.BLANK;
    private static final int ACTIVATION_COUNTER = 0;
    private static final EnumMap<Direction, Integer> ENUMMAP = 
            CodiUtils.newFilledEnumMap(() -> 0);

    /**
     * Test for {@link StateToCellFunction#apply(casim.model.codi.cell.attributes.CoDiCellState)}.
     */
    @Test
    void testApply() {
        final StateToCellFunction function = new StateToCellFunction();
        final CoDiCell cell = function.apply(STATE);
        Assert.assertEquals(cell.getState(), STATE);
        Assert.assertEquals(cell.getActivationCounter(), ACTIVATION_COUNTER);
        Assert.assertEquals(cell.getState(), STATE);
        Assert.assertEquals(cell.getNeighborsPreviousInput(), ENUMMAP);
        Assert.assertTrue(cell.getGate().isEmpty());
    }
}
