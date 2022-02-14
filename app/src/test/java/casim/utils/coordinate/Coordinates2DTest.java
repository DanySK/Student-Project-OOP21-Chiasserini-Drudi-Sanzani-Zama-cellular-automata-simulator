package casim.utils.coordinate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Coordinates}.
 */
class Coordinates2DTest {

    static final int XVALUE = 5;
    static final int YVALUE = 10;

    /**
     * Test for {@link Coordinates#getX()} method. 
     */
    @Test
    void testGetX() {
        final Coordinates2D<Integer> coord = CoordinatesUtil.of(5,  10);
        Assert.assertEquals((int) coord.getX(), XVALUE);
    }

    /**
     * Test for {@link Coordinates#getY()} method.
     */
    @Test
    void testGetY() {
        final Coordinates2D<Integer> coord = CoordinatesUtil.of(5, 10);
        Assert.assertEquals((int) coord.getY(), YVALUE);
    }

}
