package casim.model;

import casim.utils.coordinate.Coordinates;

/**
 * A {@link Cell} of the {@link Grid}.
 */
public interface Cell {

    /**
     * Return the {@link Coordinates} which describes the position of the {@link Cell}.
     * 
     * @param <T> the type of the coordinates system.
     * @return the {@link Coordinates} of the {@link Cell}. 
     */
    <T extends Number> Coordinates<T> getCoord();

    /** 
     * Return the current {@link CellState} of the {@link Cell}.
     * 
     * @return {@link CellState} the state of the {@link Cell}.
     */
    CellState getState();

}
