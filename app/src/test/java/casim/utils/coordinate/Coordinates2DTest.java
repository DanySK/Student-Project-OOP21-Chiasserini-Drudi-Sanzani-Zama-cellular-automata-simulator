package casim.utils.coordinate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Coordinates2D}.
 */
class Coordinates2DTest {

    static final int XVALUE = 5;
    static final int YVALUE = 10;
    static final Coordinates2D<Integer> COORD = CoordinatesUtil.of(5,  10);

    /**
     * Test for {@link Coordinates2D#getX()} method. 
     */
    @Test
    void testGetX() {
        Assert.assertEquals((int) COORD.getX(), XVALUE);
    }

    /**
     * Test for {@link Coordinates2D#getY()} method.
     */
    @Test
    void testGetY() {
        Assert.assertEquals((int) COORD.getY(), YVALUE);
    }

    /**
     * Test for {@link Coordinates2D#equals(Object)} method.
     */
    @Test
    void testEquals() {
        Assert.assertTrue(COORD.equals(COORD));
        var coord01 = CoordinatesUtil.of(XVALUE, YVALUE);
        Assert.assertTrue(COORD.equals(coord01));
        coord01 = CoordinatesUtil.of(YVALUE, XVALUE);
        Assert.assertFalse(COORD.equals(coord01));
    }
}
