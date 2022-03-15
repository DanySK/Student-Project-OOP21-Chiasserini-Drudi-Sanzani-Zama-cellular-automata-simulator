package casim.langtonsant;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import casim.model.langtonsant.Ant;
import casim.model.langtonsant.CellState;
import casim.model.langtonsant.Direction;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.CoordinatesUtil;

/**
 * Test class for {@link Ant}.
 */
public class AntTest {
    
    private static final Coordinates2D<Integer> STARTINGPOSITION = CoordinatesUtil.of(0, 0);
    private static final Coordinates2D<Integer> NORTHRESULT = CoordinatesUtil.of(0, 1);
    private static final Coordinates2D<Integer> EASTRESULT = CoordinatesUtil.of(1, 0);
    private static final Ant ANT = new Ant(Direction.NORTH, STARTINGPOSITION);
    
    /**
     * Test for {@link Ant#move()} method.
     */
    @Test
    void testMove() {
        ANT.move();
        Assert.assertEquals(NORTHRESULT, ANT.getPosition());
        ANT.setDirection(Direction.SOUTH);
        ANT.move();
        Assert.assertEquals(STARTINGPOSITION, ANT.getPosition());
        ANT.setDirection(Direction.EAST);
        ANT.move();
        Assert.assertEquals(EASTRESULT, ANT.getPosition());
    }
    
    
    /**
     * Test for {@link Ant#turn(CellState)} method.
     */
    @Test
    void testTurn() {
        ANT.setDirection(Direction.NORTH);
        ANT.turn(CellState.OFF);
        Assert.assertEquals(Direction.EAST, ANT.getDirection());
        ANT.setDirection(Direction.NORTH);
        ANT.turn(CellState.ON);
        Assert.assertEquals(Direction.WEST, ANT.getDirection());
        ANT.setDirection(Direction.WEST);
        ANT.turn(CellState.OFF);
        Assert.assertEquals(Direction.NORTH, ANT.getDirection());
    }

}
