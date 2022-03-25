package casim.utils.grid;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import casim.utils.coordinate.Coordinates2D;
import casim.utils.range.Ranges;

/**
 * Implementation of {@link RowGrid}.
 * 
 * @param <T> the type contained in the {@link RowGrid}.
 */
public class RowGrid<T> implements Grid2D<T> {

    private final Grid2D<T> grid;

    /**
     * Costructor of a {@link RowGrid}.
     * 
     * @param grid the {@link Grid2D} used for crate a {@link RowGrid}.
     */
    public RowGrid(final Grid2D<T> grid) {
        this.grid = grid;
    }

    /**
     * Get one specific row of the {@link RowGrid}.
     * 
     * @param row the index of row to return.
     */
    public List<T> getRow(final int row) {
        final List<T> list = new ArrayList<>();
        Ranges.of(0, list.size())
            .forEach(col -> list.add(this.get(row, col)));
        return list;
    }

    /**
     * Set one specific row of the {@link RowGrid}.
     * 
     * @param row the row to set.
     * @param list the list of element to substitute to the element contains in that row.
     */
    public void setRow(final int row, final List<T> list) {
        if (list.size() != this.getWidth()) {
            throw new InvalidParameterException("Wrong list size");
        }
        Ranges.of(0, list.size())
            .forEach(col -> this.set(row, col, list.get(col)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.grid.getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return this.grid.getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(Coordinates2D<Integer> coord) {
        return this.grid.get(coord);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(Coordinates2D<Integer> coord, T value) {
        this.grid.set(coord, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCoordValid(Coordinates2D<Integer> coord) {
        return this.grid.isCoordValid(coord);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<T> stream() {
        return this.grid.stream();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(int row, int column) {
        return this.grid.get(row, column);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(int row, int column, T value) {
        this.grid.set(row, column, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <O> Grid2D<O> map(Function<T, O> mapper) {
        return this.grid.map(mapper);
    }
}
