package casim.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import casim.utils.grid.Grid;
import casim.utils.grid.GridImpl;

/**
 * Test class for {@link Grid}.
 */
class GridTest {

    private static final int DEFAULT_VALUE = 1;
    private static final int ROWS = 3;
    private static final int COLS = 2;

    private Grid<Integer> getGrid() {
        return new GridImpl<>(3, 2);
    }

    /**
     * Test for {@link Grid#get(int, int)} method.
     */
    @Test
    void testSetAndGet() {
        final var grid = getGrid();
        assertEquals(Result.ofEmpty(), grid.set(0, 1, DEFAULT_VALUE));
        assertEquals(Result.of(DEFAULT_VALUE), grid.get(0, 1));
        assertEquals(IndexOutOfBoundsException.class, grid.get(0, COLS).getError().getClass());
        assertEquals(IndexOutOfBoundsException.class, grid.get(ROWS, 0).getError().getClass());
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
}
