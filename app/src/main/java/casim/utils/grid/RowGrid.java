package casim.utils.grid;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import casim.utils.coordinate.Coordinates2D;
import casim.utils.range.Ranges;

public class RowGrid<T> implements Grid2D<T> {

    private final Grid2D<T> grid;

    public RowGrid(final Grid2D<T> grid) {
        this.grid = grid;
    }

    public List<T> getRow(final int row) {
        final List<T> list = new ArrayList<>();
        Ranges.of(0, list.size())
            .forEach(col -> list.add(this.get(row, col)));
        return list;
    }

    public void setRow(final int row, final List<T> list) {
        if (list.size() != this.getWidth()) {
            throw new InvalidParameterException("Wrong list size");
        }
        Ranges.of(0, list.size())
            .forEach(col -> this.set(row, col, list.get(col)));
    }

    @Override
    public int getWidth() {
        return this.grid.getWidth();
    }

    @Override
    public int getHeight() {
        return this.grid.getHeight();
    }

    @Override
    public T get(Coordinates2D<Integer> coord) {
        return this.grid.get(coord);
    }

    @Override
    public void set(Coordinates2D<Integer> coord, T value) {
        this.grid.set(coord, value);
    }

    @Override
    public boolean isCoordValid(Coordinates2D<Integer> coord) {
        return this.grid.isCoordValid(coord);
    }

    @Override
    public Stream<T> stream() {
        return this.grid.stream();
    }

    @Override
    public T get(int row, int column) {
        return this.grid.get(row, column);
    }

    @Override
    public void set(int row, int column, T value) {
        this.grid.set(row, column, value);
    }

    @Override
    public <O> Grid2D<O> map(Function<T, O> mapper) {
        return this.grid.map(mapper);
    }
    
}
