package casim.model.rule.function;

import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import casim.model.cell.implementations.Cell2D;
import casim.model.cell.interfaces.Cell;
import casim.utils.coordinate.Coordinates;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid;

/**
 * General function used to calculate neighbors of a {@link Cell} in a 2D grid.
 * 
 * @param <T> the type of the finite states a {@link Cell} can assume.
 */
public class Neighbors2DFunction<T> implements BiFunction<Cell2D<T>, Grid<Coordinates<Integer>, Cell<T>>, Iterable<Cell<T>>> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Cell<T>> apply(final Cell2D<T> cell, final Grid<Coordinates<Integer>, Cell<T>> grid) {
        return Stream.of(CoordinatesUtil.of(1, 0), CoordinatesUtil.of(0, 1), 
                          CoordinatesUtil.of(0, -1), CoordinatesUtil.of(-1, 0))
                     .map(coord -> CoordinatesUtil.sumInt(coord, cell.getCoordinates()))
                     .filter(grid::isCoordValid)
                     .map(coord -> new Cell2D<>(coord, grid.get(coord).getAttributes())) 
                     .collect(Collectors.toList());
    }

}
