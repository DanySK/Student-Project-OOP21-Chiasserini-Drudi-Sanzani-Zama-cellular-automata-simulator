package casim.utils.coordinate;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link CoordinatesUtil}.
 */
class CoordinatesUtilTest {

    static final Coordinates<Integer> INTCOORD01 = CoordinatesUtil.of(10, 5);
    static final Coordinates<Integer> INTCOORD02 = CoordinatesUtil.of(15, 10);
    static final Coordinates<Integer> INTCOORD03 = CoordinatesUtil.of(15, 5);
    static final Coordinates<Integer> INTCOORD04 = CoordinatesUtil.of(15, 0);
    static final Coordinates<Float> FLOATCOORD01 = CoordinatesUtil.of((float) 10.5, (float) 4.5);
    static final Coordinates<Float> FLOATCOORD02 = CoordinatesUtil.of((float) 15.6, (float) 6.5);
    static final Coordinates<Float> FLOATCOORD03 = CoordinatesUtil.of((float) 9.0, (float) 7.5);
    static final Coordinates<Float> FLOATCOORD04 = CoordinatesUtil.of((float) 7.6, (float) 6.5);
    static final Coordinates<Double> DOUBLECOORD01 = CoordinatesUtil.of(5.5, 10.5);
    static final Coordinates<Double> DOUBLECOORD02 = CoordinatesUtil.of(7.4, 12.6);
    static final Coordinates<Short> SHORTCOORD01 = CoordinatesUtil.of((short) 5, (short) 10);
    static final Coordinates<Short> SHORTCOORD02 = CoordinatesUtil.of((short) 7, (short) 17);
    static final Coordinates<Long> LONGCOORD01 = CoordinatesUtil.of((long) 20, (long) 10);
    static final Coordinates<Long> LONGCOORD02 = CoordinatesUtil.of((long) 25, (long) 25);

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
        Assert.assertTrue(CoordinatesUtil.isValid(coordI, INTCOORD01, INTCOORD02));
        Assert.assertFalse(CoordinatesUtil.isValid(coordI, INTCOORD03, INTCOORD04));
        Assert.assertTrue(CoordinatesUtil.isValid(coordF, FLOATCOORD01, FLOATCOORD02));
        Assert.assertFalse(CoordinatesUtil.isValid(coordF, FLOATCOORD03, FLOATCOORD04));
        Assert.assertTrue(CoordinatesUtil.isValid(coordD, DOUBLECOORD01, DOUBLECOORD02));
        Assert.assertTrue(CoordinatesUtil.isValid(coordS, SHORTCOORD01, SHORTCOORD02));
        Assert.assertTrue(CoordinatesUtil.isValid(coordL, LONGCOORD01, LONGCOORD02));
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
        Assert.assertTrue(CoordinatesUtil.isValid(coordI, INTCOORD02));
        Assert.assertFalse(CoordinatesUtil.isValid(coordI, INTCOORD01));
        Assert.assertTrue(CoordinatesUtil.isValid(coordF, FLOATCOORD02));
        Assert.assertTrue(CoordinatesUtil.isValid(coordD, DOUBLECOORD02));
        Assert.assertTrue(CoordinatesUtil.isValid(coordS, SHORTCOORD02));
        Assert.assertTrue(CoordinatesUtil.isValid(coordL, LONGCOORD02));
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
        Assert.assertTrue(CoordinatesUtil.isValid(coordI, INTCOORD02.getX(), INTCOORD02.getY()));
        Assert.assertFalse(CoordinatesUtil.isValid(coordI, INTCOORD01.getX(), INTCOORD01.getY()));
        Assert.assertTrue(CoordinatesUtil.isValid(coordF, FLOATCOORD02.getX(), FLOATCOORD02.getY()));
        Assert.assertTrue(CoordinatesUtil.isValid(coordD, DOUBLECOORD02.getX(), DOUBLECOORD02.getY()));
        Assert.assertTrue(CoordinatesUtil.isValid(coordS, SHORTCOORD02.getX(), SHORTCOORD02.getY()));
        Assert.assertTrue(CoordinatesUtil.isValid(coordL, LONGCOORD02.getX(), LONGCOORD02.getY()));
    }
}
