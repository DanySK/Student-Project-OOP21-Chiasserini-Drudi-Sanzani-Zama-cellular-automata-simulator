package casim.utils.coordinate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link CoordinatesUtil}.
 */
class CoordinatesUtilTest {

    static final Coordinates2D<Integer> INTCOORD01 = CoordinatesUtil.of(10, 5);
    static final Coordinates2D<Integer> INTCOORD02 = CoordinatesUtil.of(15, 10);
    static final Coordinates2D<Integer> INTCOORD03 = CoordinatesUtil.of(15, 5);
    static final Coordinates2D<Integer> INTCOORD04 = CoordinatesUtil.of(15, 0);
    static final Coordinates2D<Double> DOUBLECOORD01 = CoordinatesUtil.of(5.5, 10.5);
    static final Coordinates2D<Double> DOUBLECOORD02 = CoordinatesUtil.of(7.4, 12.6);


    /**
     * Test for {@link CoordinatesUtil#sum(Coordinates, Coordinates)} method.
     */
    @Test
    void testSum() {
        final Coordinates2D<Integer> intSum = CoordinatesUtil.sum(INTCOORD01, INTCOORD02);
        Assert.assertEquals((int)intSum.getX(), INTCOORD01.getX() + INTCOORD02.getX());
        final Coordinates2D<Integer> floatSum = CoordinatesUtil.sum(DOUBLECOORD01, DOUBLECOORD02);
        Assert.assertEquals((int)floatSum.getX(), DOUBLECOORD01.getX().intValue() + DOUBLECOORD02.getX().intValue());
    }

    /**
     * Test for {@link CoordinatesUtil#isValid(Coordinates, Coordinates, Coordinates)} method.
     */
    @Test
    void testIsValid() {
        final var coordI = CoordinatesUtil.of(10, 5);
        final var coordD = CoordinatesUtil.of(5.5, 10.5);
        Assert.assertTrue(CoordinatesUtil.isValid(coordI, INTCOORD01, INTCOORD02));
        Assert.assertFalse(CoordinatesUtil.isValid(coordI, INTCOORD03, INTCOORD04));
        Assert.assertTrue(CoordinatesUtil.isValid(coordD, DOUBLECOORD01, DOUBLECOORD02));
    }

    /**
     * Test for {@link CoordinatesUtil#isValid(Coordinates, Coordinates)} method.
     */
    @Test
    void testIsValidOverload01() {
        final var coordI = CoordinatesUtil.of(10, 5);
        final var coordD = CoordinatesUtil.of(5.5, 10.5);
        Assert.assertTrue(CoordinatesUtil.isValid(coordI, INTCOORD02));
        Assert.assertFalse(CoordinatesUtil.isValid(coordI, INTCOORD01));
        Assert.assertTrue(CoordinatesUtil.isValid(coordD, DOUBLECOORD02));
    }

    /**
     * Test for {@link CoordinatesUtil#isValid(Coordinates, Number, Number)} method.
     */
    @Test
    void testIsValidOverload02() {
        final var coordI = CoordinatesUtil.of(10, 5);
        final var coordD = CoordinatesUtil.of(5.5, 10.5);
        Assert.assertTrue(CoordinatesUtil.isValid(coordI, INTCOORD02.getX(), INTCOORD02.getY()));
        Assert.assertFalse(CoordinatesUtil.isValid(coordI, INTCOORD01.getX(), INTCOORD01.getY()));
        Assert.assertTrue(CoordinatesUtil.isValid(coordD, DOUBLECOORD02.getX(), DOUBLECOORD02.getY()));
    }
}
