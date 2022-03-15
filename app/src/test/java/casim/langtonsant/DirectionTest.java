package casim.langtonsant;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import casim.model.langtonsant.CellState;
import casim.model.langtonsant.Direction;

public class DirectionTest {

    /**
     * Test for {@link Direction#turn(Direction, CellState)} method.
     */
    @Test
    void testTurn() {
        Assert.assertEquals(Direction.EAST, Direction.turn(Direction.NORTH, CellState.OFF));
        Assert.assertEquals(Direction.WEST, Direction.turn(Direction.NORTH, CellState.ON));
        Assert.assertEquals(Direction.NORTH, Direction.turn(Direction.WEST, CellState.OFF));
    }
}
