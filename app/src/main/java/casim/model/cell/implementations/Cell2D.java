package casim.model.cell.implementations;

import casim.model.cell.interfaces.Cell;
import casim.model.cell.interfaces.CellAttributes;
import casim.utils.coordinate.Coordinates;
import casim.utils.coordinate.Coordinates2D;

/**
 * Implementation of a generic 2D {@link Cell}, it doesn't specify the finite states type.
 * 
 *  @param <T> the type of the finite states of the {@link Automaton}'s {@link Cell}.
 */
public class Cell2D<T> implements Cell<T> {

    private final Coordinates<Integer> coord;
    private final CellAttributes<T> attributes;

    /**
     * Construct a new {@link Abstract2DCell}.
     * 
     * @param coord the {@link Coordinates} of the {@link Cell}.
     * @param attributes the {@link CellAttributes} of the {@link Cell}.
     */
    public Cell2D(final Coordinates2D<Integer> coord, final CellAttributes<T> attributes) {
        this.coord = coord;
        this.attributes = attributes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Coordinates<Integer> getCoordinates() {
        return this.coord;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CellAttributes<T> getAttributes() {
        return this.attributes;
    };

}
