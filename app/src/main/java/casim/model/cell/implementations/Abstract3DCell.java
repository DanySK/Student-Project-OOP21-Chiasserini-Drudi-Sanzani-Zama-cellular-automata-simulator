package casim.model.cell.implementations;

import casim.model.cell.interfaces.Cell;
import casim.model.cell.interfaces.CellAttributes;
import casim.utils.coordinate.Coordinates;
import casim.utils.coordinate.Coordinates3D;

/**
 * An abstract which describes a generic 3D {@link Cell}, it doesn't specify the finite states type.
 * 
 * @param <T> the type of the finite states of the {@link Cell}.
 */
public abstract class Abstract3DCell<T> implements Cell<T> {

    private final Coordinates<Integer> coordinates;

    /**
     * Construct a new {@link Coordinates3D}.
     * 
     * @param coordinates the coordinates of the cell.
     */
    public Abstract3DCell(final Coordinates3D<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * {@inheritDoc}.
     */
    public Coordinates<Integer> getCoordinates() {
        return this.coordinates;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public abstract CellAttributes<T> getAttributes();
}
