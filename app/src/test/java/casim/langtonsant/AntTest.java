package casim.langtonsant;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import casim.model.langtonsant.Ant;
import casim.model.langtonsant.Direction;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.CoordinatesUtil;

/**
 * Test class for {@link Ant}.
 */
public class AntTest {
    
    private static final Coordinates2D<Integer> startingPosition = CoordinatesUtil.of(0, 0);
    private static final Coordinates2D<Integer> northResult = CoordinatesUtil.of(0, 1);
    private static final Coordinates2D<Integer> eastResult = CoordinatesUtil.of(1, 0);

    /**
     * Test for {@link Ant#move()} method.
     */
    @Test
    void testMove() {
        final var ant = new Ant(Direction.NORTH, startingPosition);
        ant.move();
        Assert.assertEquals(northResult, ant.getPosition());
        ant.setDirection(Direction.SOUTH);
        ant.move();
        Assert.assertEquals(startingPosition, ant.getPosition());
        ant.setDirection(Direction.EAST);
        ant.move();
        Assert.assertEquals(eastResult, ant.getPosition());
    }

}
