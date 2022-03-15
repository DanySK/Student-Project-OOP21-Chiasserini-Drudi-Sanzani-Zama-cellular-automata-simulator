package casim.langtonsant;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import casim.model.langtonsant.CellState;
import casim.model.langtonsant.Direction;

public class DirectionTest {

    @Test
    void testTurn() {
        Assert.assertEquals(Direction.EAST, Direction.turn(Direction.NORTH, CellState.OFF));
        Assert.assertEquals(Direction.WEST, Direction.turn(Direction.NORTH, CellState.ON));
    }
}
