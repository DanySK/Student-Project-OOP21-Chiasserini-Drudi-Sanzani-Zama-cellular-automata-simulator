package casim.utils.coordinate;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link CoordinatesUtil}.
 */
class CoordinatesUtilTest {

    /**
     * Test for {@link CoordinatesUtil#sum(Coordinates, Coordinates)} method.
     */
    @Test
    void testSum() {
        fail();
    }

    /**
     * Test for {@link CoordinatesUtil#isValid(Coordinates, Coordinates, Coordinates)} method.
     */
    @Test
    void testIsValid() {
        final var coordI = CoordinatesUtil.of(10, 5);
        final var coordF = CoordinatesUtil.of((float) 10.5, (float) 5.5);
        final var coordD = CoordinatesUtil.of(5.5, 10.5);
        final var coordS = CoordinatesUtil.of((short) 5, (short) 10);
        final var coordL = CoordinatesUtil.of((long) 20, (long) 10);
        Assert.assertTrue(CoordinatesUtil.isValid(coordI, CoordinatesUtil.of(10, 5), CoordinatesUtil.of(15, 10)));
        Assert.assertFalse(CoordinatesUtil.isValid(coordI, CoordinatesUtil.of(15, 5), CoordinatesUtil.of(15, 0)));
        Assert.assertTrue(CoordinatesUtil.isValid(coordF, CoordinatesUtil.of((float) 10.5, (float) 4.5), CoordinatesUtil.of((float) 15.6, (float) 6.5)));
        Assert.assertFalse(CoordinatesUtil.isValid(coordF, CoordinatesUtil.of((float) 9.0, (float) 7.5), CoordinatesUtil.of((float) 7.6, (float) 6.5)));
        Assert.assertTrue(CoordinatesUtil.isValid(coordD, CoordinatesUtil.of(5.5, 10.5), CoordinatesUtil.of(7.4, 12.6)));
        Assert.assertFalse(CoordinatesUtil.isValid(coordD, CoordinatesUtil.of(0.0, 20.9), CoordinatesUtil.of(5.5, 10.5)));
        Assert.assertTrue(CoordinatesUtil.isValid(coordS, CoordinatesUtil.of((short) 5, (short) 10), CoordinatesUtil.of((short) 7, (short) 17)));
        Assert.assertTrue(CoordinatesUtil.isValid(coordL, CoordinatesUtil.of((long) 20, (long) 10), CoordinatesUtil.of((long) 25, (long) 25)));
    }

    /**
     * Test for {@link CoordinatesUtil#isValid(Coordinates, Coordinates)} method.
     */
    @Test
    void testIsValidOverload01() {
        final var coordI = CoordinatesUtil.of(10, 5);
        final var coordF = CoordinatesUtil.of((float) 10.5, (float) 5.5);
        final var coordD = CoordinatesUtil.of(5.5, 10.5);
        final var coordS = CoordinatesUtil.of((short) 5, (short) 10);
        final var coordL = CoordinatesUtil.of((long) 20, (long) 10);
        Assert.assertTrue(CoordinatesUtil.isValid(coordI, CoordinatesUtil.of(14, 29)));
        Assert.assertFalse(CoordinatesUtil.isValid(coordI, CoordinatesUtil.of(4, 7)));
        Assert.assertTrue(CoordinatesUtil.isValid(coordF, CoordinatesUtil.of((float) 13.7, (float) 7.8)));
        Assert.assertTrue(CoordinatesUtil.isValid(coordD, CoordinatesUtil.of(6.9, 34.9)));
        Assert.assertTrue(CoordinatesUtil.isValid(coordS, CoordinatesUtil.of((short) 7, (short) 16)));
        Assert.assertTrue(CoordinatesUtil.isValid(coordL, CoordinatesUtil.of((long) 35, (long) 24)));
    }

    /**
     * Test for {@link CoordinatesUtil#isValid(Coordinates, Number, Number)} method.
     */
    @Test
    void testIsValidOverload02() {
        final var coordI = CoordinatesUtil.of(10, 5);
        final var coordF = CoordinatesUtil.of((float) 10.5, (float) 5.5);
        final var coordD = CoordinatesUtil.of(5.5, 10.5);
        final var coordS = CoordinatesUtil.of((short) 5, (short) 10);
        final var coordL = CoordinatesUtil.of((long) 20, (long) 10);
        Assert.assertTrue(CoordinatesUtil.isValid(coordI, 14, 8));
        Assert.assertFalse(CoordinatesUtil.isValid(coordI, 5, 18));
        Assert.assertTrue(CoordinatesUtil.isValid(coordF, (float) 13.2, (float) 7.6));
        Assert.assertTrue(CoordinatesUtil.isValid(coordD, 7.3, 19.0));
        Assert.assertTrue(CoordinatesUtil.isValid(coordS, (short) 8, (short) 20));
        Assert.assertTrue(CoordinatesUtil.isValid(coordL, (long) 34, (long) 18));
    }
}
