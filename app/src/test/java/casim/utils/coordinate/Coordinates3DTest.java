package casim.utils.coordinate;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link Coordinates3D}.
 */
public class Coordinates3DTest {

    static final int XVALUE = 5;
    static final int YVALUE = 10;
    static final int ZVALUE = 15;
    static final Coordinates3D<Integer> coord = CoordinatesUtil.of(XVALUE, YVALUE, ZVALUE);
    /**
     * Test for {@link Coordinates3D#getX()} method.
     */
    @Test
    void testGetX() {
        Assert.assertEquals(XVALUE, (int)coord.getX());
    }

    /**
     * Test for {@link Coordinates3D#getY()} method.
     */
    @Test
    void testGetY() {
        Assert.assertEquals(YVALUE, (int)coord.getY());
    }

    /**
     * Test for {@link Coordinates3D#getZ()} method.
     */
    void testGetZ() {
        Assert.assertEquals(ZVALUE, (int)coord.getZ());
    }

    /**
     * Test for {@link Coordinates3D#equals(Object)} method.
     */
    @Test
    void testEquals() {
        Assert.assertTrue(coord.equals(coord));
        var coord01 = CoordinatesUtil.of(XVALUE, YVALUE, ZVALUE);
        Assert.assertTrue(coord.equals(coord01));
        coord01 = CoordinatesUtil.of(YVALUE, XVALUE, ZVALUE);
        Assert.assertFalse(coord.equals(coord01));
    }
}
