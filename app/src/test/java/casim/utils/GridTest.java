package casim.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Grid}.
 */
class GridTest {
    
    private static final int DEFAULT_VALUE = 1;
    private static final int ROWS = 3;
    private static final int COLS = 2;

    private Grid<Integer> getGrid() {
        return null;
    }

    /**
     * Test for {@link Grid#get(int, int)} method.
     */
    @Test
    void testGet() {
        final var grid = getGrid();
        assertEquals(DEFAULT_VALUE, grid.get(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.get(0, COLS));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.get(ROWS, 0));
    }

    /**
     * Test for {@link Grid#getHeight()} method.
     */
    @Test
    void testGetHeight() {
        final var grid = getGrid();
        assertEquals(ROWS, grid.getHeight());
    }

    /**
     * Test for {@link Grid#getWidth()} method.
     */
    @Test
    void testGetWidth() {
        final var grid = getGrid();
        assertEquals(COLS, grid.getWidth());
    }

    /**
     * Test for {@link Grid#set(int, int, Object)} method.
     */
    @Test
    void testSet() {
        final int newVal = 2;
        final var grid = getGrid();
        grid.set(0, 1, newVal);
        assertEquals(newVal, grid.get(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.set(0, COLS, newVal));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.set(ROWS, 0, newVal));
    }
}
