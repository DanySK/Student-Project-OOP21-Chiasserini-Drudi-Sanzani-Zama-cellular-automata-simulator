package casim.controller;

import java.util.Iterator;
import casim.utils.grid.Grid2D;

/**
 * Interface for the automaton controller.
 *
 * @param <T>
 */
public interface AutomatonController<T> extends Iterator<Grid2D<T>> {
    /**
     * Get the current state grid.
     * 
     * @return the state grid.
     */
    Grid2D<T> getGrid();
}
