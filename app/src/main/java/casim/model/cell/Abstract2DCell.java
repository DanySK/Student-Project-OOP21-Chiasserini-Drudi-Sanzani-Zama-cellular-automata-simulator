package casim.model.cell;

import casim.utils.coordinate.Coordinates;
import casim.utils.coordinate.Coordinates2D;

/**
 * An abstract which describes a generic 2D {@link Cell}, it doesn't specify the finite states type.
 * 
 *  @param <T> the type of the finite states of the {@link Automaton}'s {@link Cell}.
 */
public abstract class Abstract2DCell<T> implements Cell<T> {

    private final Coordinates<Integer> coord;

    /**
     * Construct a new {@link Abstract2DCell}.
     * 
     * @param coord the coordinates of the cell.
     */
    public Abstract2DCell(final Coordinates2D<Integer> coord) {
        this.coord = coord;
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
    public abstract CellAttributes<T> getAttributes();

}
