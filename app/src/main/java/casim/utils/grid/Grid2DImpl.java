package casim.utils.grid;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import casim.utils.Empty;
import casim.utils.Result;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.range.Ranges;

/**
 * Implementation of {@link Grid2D}.
 * 
 * @param <T> the type of the elements contained in {@link Grid2DImpl}.
 */
public class Grid2DImpl<T> implements Grid2D<T> {

    private final int rows;
    private final int columns;
    private final Map<Coordinates2D<Integer>, T> grid = new HashMap<>();

    /**
     * Construct a new {@link Grid2D} filled with nulls.
     * 
     * @param rows the number of the rows of the {@link Grid2D}.
     * @param columns the number of the columns of the {@link Grid2D}.
     */
    public Grid2DImpl(final int rows, final int columns) {
        this(rows, columns, () -> null);
    }

    /**
     * Construct a new {@link Grid2D} with a default value supplier.
     * 
     * @param rows the number of the rows of the {@link Grid2D}.
     * @param columns the number of the columns of the {@link Grid2D}.
     * @param defaultValue the default value supplier.
     */
    public Grid2DImpl(final int rows, final int columns, final Supplier<T> defaultValue) {
        this.rows = rows;
        this.columns = columns;
        for (final var x : Ranges.of(0, rows)) {
            for (final var y : Ranges.of(0, columns)) {
                this.grid.put(CoordinatesUtil.of(x, y), defaultValue.get());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.columns;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return this.rows;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Result<T> get(final int row, final int column) {
        return this.get(CoordinatesUtil.of(row, column));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Result<Empty> set(final int row, final int column, final T value) {
        return this.set(CoordinatesUtil.of(row, column), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Result<T> get(final Coordinates2D<Integer> coord) {
        return Result.ofEmpty()
            .require(x -> this.isCoordValid(coord), new IndexOutOfBoundsException())
            .map(x -> this.grid.get(coord));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Result<Empty> set(final Coordinates2D<Integer> coord, final T value) {
        if (!this.isCoordValid(coord)) {
            return Result.error(new IndexOutOfBoundsException());
        }
        this.grid.put(coord, value);
        return Result.ofEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCoordValid(final Coordinates2D<Integer> coord) {
        return CoordinatesUtil.isValid(coord, this.rows, this.columns);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<T> stream() {
        return this.grid.values().stream();
    }
}
