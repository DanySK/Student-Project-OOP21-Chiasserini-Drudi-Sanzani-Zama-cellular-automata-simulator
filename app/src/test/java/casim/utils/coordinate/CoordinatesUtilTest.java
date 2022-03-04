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
    static final Coordinates3D<Integer> INTCOORD05 = CoordinatesUtil.of(10, 5, 12);
    static final Coordinates3D<Integer> INTCOORD06 = CoordinatesUtil.of(15, 10, 20);
    static final Coordinates3D<Integer> INTCOORD07 = CoordinatesUtil.of(15, 10, 4);
    static final Coordinates3D<Integer> INTCOORD08 = CoordinatesUtil.of(15, 0, 10);
    static final Coordinates3D<Double> DOUBLECOORD03 = CoordinatesUtil.of(5.5, 10.5, 7.6);
    static final Coordinates3D<Double> DOUBLECOORD04 = CoordinatesUtil.of(7.4, 12.6, 14.9);


    /**
     * Test for {@link CoordinatesUtil#sumInt(Coordinates2D, Coordinates2D)} method.
     */
    @Test
    void testSum2D() {
        final Coordinates2D<Integer> intSum = CoordinatesUtil.sumInt(INTCOORD01, INTCOORD02);
        Assert.assertEquals(intSum.getX().intValue(), INTCOORD01.getX() + INTCOORD02.getX());
    }

    /**
     * Test for {@link CoordinatesUtil#sumInt(Coordinates3D, Coordinates3D)} method.
     */
    @Test
    void testSum3D() {
        final Coordinates3D<Integer> intSum02 = CoordinatesUtil.sumInt(INTCOORD05, INTCOORD06);
        Assert.assertEquals(intSum02.getX().intValue(), INTCOORD05.getX() + INTCOORD06.getX());
    }

    /**
     * Test for {@link CoordinatesUtil#isValid(Coordinates2D, Coordinates2D, Coordinates2D)} method.
     */
    @Test
    void testIsValid2D() {
        final var coordI = CoordinatesUtil.of(10, 5);
        final var coordD = CoordinatesUtil.of(5.5, 10.5);
        Assert.assertTrue(CoordinatesUtil.isValid(coordI, INTCOORD01, INTCOORD02));
        Assert.assertFalse(CoordinatesUtil.isValid(coordI, INTCOORD03, INTCOORD04));
        Assert.assertTrue(CoordinatesUtil.isValid(coordD, DOUBLECOORD01, DOUBLECOORD02));
    }

    /**
     * Test for {@link CoordinatesUtil#isValid(Coordinates3D, Coordinates3D, Coordinates3D)} method.
     */
    @Test
    void testIsValid3D() {
        final var coord3DI = CoordinatesUtil.of(10, 5, 12);
        final var coord3DD = CoordinatesUtil.of(5.5, 10.5, 7.6);
        Assert.assertTrue(CoordinatesUtil.isValid(coord3DI, INTCOORD05, INTCOORD06));
        Assert.assertFalse(CoordinatesUtil.isValid(coord3DI, INTCOORD07, INTCOORD08));
        Assert.assertTrue(CoordinatesUtil.isValid(coord3DD, DOUBLECOORD03, DOUBLECOORD04));
    }

    /**
     * Test for {@link CoordinatesUtil#isValid(Coordinates2D, Coordinates2D)} method.
     */
    @Test
    void testIsValid2DWithOneCoord() {
        final var coordI = CoordinatesUtil.of(10, 5);
        final var coordD = CoordinatesUtil.of(5.5, 10.5);
        Assert.assertTrue(CoordinatesUtil.isValid(coordI, INTCOORD02));
        Assert.assertFalse(CoordinatesUtil.isValid(coordI, INTCOORD01));
        Assert.assertTrue(CoordinatesUtil.isValid(coordD, DOUBLECOORD02));
    }

    /**
     * Test for {@link CoordinatesUtil#isValid(Coordinates3D, Coordinates3D)} method.
     */
    @Test
    void testIsValid3DWithOneCoord() {
        final var coord3DI = CoordinatesUtil.of(10, 5, 12);
        final var coord3DD = CoordinatesUtil.of(5.5, 10.5, 7.6);
        Assert.assertTrue(CoordinatesUtil.isValid(coord3DI, INTCOORD06));
        Assert.assertFalse(CoordinatesUtil.isValid(coord3DI, INTCOORD05));
        Assert.assertTrue(CoordinatesUtil.isValid(coord3DD, DOUBLECOORD04));
    }

    /**
     * Test for {@link CoordinatesUtil#isValid(Coordinates2D, Number, Number)} method.
     */
    @Test
    void testIsValidOverload02() {
        final var coordI = CoordinatesUtil.of(10, 5);
        final var coordD = CoordinatesUtil.of(5.5, 10.5);
        Assert.assertTrue(CoordinatesUtil.isValid(coordI, INTCOORD02.getX(), INTCOORD02.getY()));
        Assert.assertFalse(CoordinatesUtil.isValid(coordI, INTCOORD01.getX(), INTCOORD01.getY()));
        Assert.assertTrue(CoordinatesUtil.isValid(coordD, DOUBLECOORD02.getX(), DOUBLECOORD02.getY()));
    }

    /**
     * Test for {@link CoordinatesUtil#isValid(Coordinates3D, Number, Number)} method.
     */
    @Test
    void testIsValid3DWithNumbers() {
        final var coord3DI = CoordinatesUtil.of(10, 5, 12);
        final var coord3DD = CoordinatesUtil.of(5.5, 10.5, 7.6);
        Assert.assertTrue(CoordinatesUtil.isValid(coord3DI, INTCOORD06.getX(), INTCOORD06.getY(), INTCOORD06.getZ()));
        Assert.assertFalse(CoordinatesUtil.isValid(coord3DI, INTCOORD05.getX(), INTCOORD05.getY(), INTCOORD05.getZ()));
        Assert.assertTrue(CoordinatesUtil.isValid(coord3DD, DOUBLECOORD04.getX(), DOUBLECOORD04.getY(), DOUBLECOORD04.getZ()));
    }
}
