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
public class Cell3D<T> implements Cell<T> {

    private final Coordinates<Integer> coordinates;
    private final CellAttributes<T> attributes;

    /**
     * Construct a new {@link Coordinates3D}.
     * 
     * @param coordinates the {@link Coordinates} of the {@link Cell}.
     * @param attributes the {@link CellAttributes} of the {@link Cell}.
     */
    public Cell3D(final Coordinates3D<Integer> coordinates, final CellAttributes<T> attributes) {
        this.coordinates = coordinates;
        this.attributes = attributes;
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
    public CellAttributes<T> getAttributes() {
        return this.attributes;
    }
}
