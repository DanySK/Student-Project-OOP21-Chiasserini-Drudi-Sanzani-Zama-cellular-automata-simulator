package casim.model.codi.cell.builder.utils;

import java.util.EnumMap;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.attributes.CellState;
import casim.model.codi.cell.attributes.Direction;
import casim.model.codi.utils.CodiUtils;

/**
 * Test class for {@link CoDiCellSupplier}.
 */
class CoDiCellSupplierTest {

    private static final int ACTIVATION_COUNTER = 0;
    private static final EnumMap<Direction, Integer> ENUMMAP = 
            CodiUtils.newFilledEnumMap(() -> 0);
    private static final CellState STATE = CellState.BLANK;

    /**
     * Test for {@link CoDiCellSupplier#get()}.
     */
    @Test
    void testGet() {
       final CoDiCellSupplier supplier = new CoDiCellSupplier();
       final CoDiCell cell = supplier.get();
       Assert.assertEquals(cell.getActivationCounter(), ACTIVATION_COUNTER);
       Assert.assertEquals(cell.getState(), STATE);
       Assert.assertEquals(cell.getNeighborsPreviousInput(), ENUMMAP);
       Assert.assertTrue(cell.getGate().isEmpty());
    }
}
