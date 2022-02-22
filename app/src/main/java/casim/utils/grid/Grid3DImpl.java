package casim.utils.grid;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.google.common.base.Supplier;

import casim.utils.coordinate.Coordinates3D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.range.Ranges;

/**
 * Implementation of {@link Grid3D}.
 * 
 * @param <T> the type contained in the {@link Grid3D}.
 */
 public class Grid3DImpl<T> implements Grid3D<T> {
	
	private final int rows;
	private final int columns;
	private final int depth;
	private final Map<Coordinates3D<Integer>, T> grid = new HashMap<>();

	/**
	 * Construct a new {@link Grid3D} filled with nulls.
	 * 
	 * @param rows the number of the rows of the grid.
	 * @param columns the number of the columns of the grid.
	 * @param depth the depth of the grid.
	 */
	public Grid3DImpl(final int rows, final int columns, final int depth) {
		this(rows, columns, depth, () -> null);
	}
	
	/**
	 * Construct a new {@link Grid3D} with a default value supplier.
	 * 
	 * @param rows the number of the rows of the grid.
	 * @param columns the number of the columns of the grid.
	 * @param depth the depth of the grid.
	 * @param defaultValue the default value supplier.
	 */
	public Grid3DImpl(final int rows, final int columns, final int depth, final Supplier<T> defaultValue) {
		this.rows = rows;
		this.columns = columns;
		this.depth = depth;
		
		for (final var x : Ranges.of(0, rows)) {
			for (final var y : Ranges.of(0, columns)) {
				for (final var z : Ranges.of(0, depth)) {
					this.grid.put(CoordinatesUtil.of(x, y, z), defaultValue.get());
				}
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
	public int getDepth() {
		return this.depth;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T get(final int row, final int column, final int depth) {
		return this.get(CoordinatesUtil.of(row, column, depth));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void set(final int row, final int column, final int depth, final T value) {
		return this.set(CoordinatesUtil.of(row, column, depth), value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T get(final Coordinates3D<Integer> coord) {
		this.throwIfOutOfBound(coord);
		return this.grid.get(coord);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void set(final Coordinates3D<Integer> coord, final T value) {
		this.throwIfOutOfBound(coord);
		this.grid.put(coord, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCoordValid(final Coordinates3D<Integer> coord) {
		return CoordinatesUtil.isValid(coord, this.rows, this.columns, this.depth);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Stream<T> stream() {
		return this.grid.values().stream();
	}

	private void throwIfOutOfBound(final Coordinates3D<Integer> coord) {
        if (!this.isCoordValid(coord)) {
            throw new IndexOutOfBoundsException("Size: " + this.getHeight() + " x " + this.getWidth() + " x " + this.getDepth() + "Coord: " + coord);
        }
    }
}
