package casim.model.cell.interfaces;

import casim.utils.coordinate.Coordinates;

/**
 * A {@link Cell} of the {@link Grid}.
 * 
 * @param <T> the type of the finite states a Cell can assume.
 */
public interface Cell<T> {

    /**
     * Return the {@link Coordinates} which describes the position of the {@link Cell}.
     * 
     * @return the {@link Coordinates} of the {@link Cell}. 
     */
    Coordinates<Integer> getCoordinates();

    /** 
     * Return the current {@link CellAttributes} of the {@link Cell}.
     * 
     * @return {@link CellAttributes} the state of the {@link Cell}.
     */
    CellAttributes<T> getAttributes();

}
