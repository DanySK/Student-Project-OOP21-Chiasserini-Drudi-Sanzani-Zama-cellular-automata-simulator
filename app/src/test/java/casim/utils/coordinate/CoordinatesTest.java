package casim.utils.coordinate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Coordinates}.
 */
class CoordinatesTest {

    /**
     * Test for {@link Coordinates#getX()} method. 
     */
    @Test
    void testGetX() {
        final Coordinates<Integer> coord = new CoordinatesImpl<>(5, 10);
        Assert.assertEquals((int)coord.getX(), 5);
    }

    /**
     * Test for {@link Coordinates#getY()} method.
     */
    @Test
    void testGetY() {
    	final Coordinates<Integer> coord = new CoordinatesImpl<>(5, 10);
        Assert.assertEquals((int)coord.getY(), 10);
    }

    /**
     * Test for {@link Coordinates#setX(Number)} method.
     */
    @Test
    void testSetX() {
    	final Coordinates<Integer> coord = new CoordinatesImpl<>(5, 10);
    	coord.setX(15);
    	Assert.assertEquals((int)coord.getX(), 15);
    }

    /**
     * Test for {@link Coordinates#setY(Number)} method.
     */
    @Test
    void testSetY() {
    	final Coordinates<Integer> coord = new CoordinatesImpl<>(5, 10);
    	coord.setY(15);
    	Assert.assertEquals((int)coord.getY(), 15);
    }
}
