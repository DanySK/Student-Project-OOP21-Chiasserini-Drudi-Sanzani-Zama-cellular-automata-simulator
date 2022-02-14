package casim.utils.coordinate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Coordinates}.
 */
class Coordinates2DTest {

    /**
     * Test for {@link Coordinates#getX()} method. 
     */
    @Test
    void testGetX() {
        final Coordinates2D<Integer> coord = CoordinatesUtil.of(5,  10);
        Assert.assertEquals((int)coord.getX(), 5);
    }

    /**
     * Test for {@link Coordinates#getY()} method.
     */
    @Test
    void testGetY() {
    	final Coordinates2D<Integer> coord = CoordinatesUtil.of(5, 10);
        Assert.assertEquals((int)coord.getY(), 10);
    }

}
