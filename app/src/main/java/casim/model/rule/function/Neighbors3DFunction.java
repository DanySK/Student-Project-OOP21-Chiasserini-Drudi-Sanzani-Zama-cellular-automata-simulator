package casim.model.rule.function;

import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import casim.model.cell.implementations.Cell3D;
import casim.model.cell.interfaces.Cell;
import casim.utils.coordinate.Coordinates;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid;

/**
 * General function used to calculate neighbors of a {@link Cell} in a 3D grid.
 * 
 * @param <T> the type of the finite states a {@link Cell} can assume.
 */
public class Neighbors3DFunction<T> implements BiFunction<Cell3D<T>, Grid<Coordinates<Integer>, Cell<T>>, Iterable<Cell<T>>> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Cell<T>> apply(final Cell3D<T> cell, final Grid<Coordinates<Integer>, Cell<T>> grid) {
        return Stream.of(CoordinatesUtil.of(1, 0, 0), CoordinatesUtil.of(0, 1, 0), 
                          CoordinatesUtil.of(0, -1, 0), CoordinatesUtil.of(-1, 0, 0))
                     .map(coord -> CoordinatesUtil.sumInt(coord, cell.getCoordinates()))
                     .filter(coord -> grid.isCoordValid(coord))
                     .map(coord -> new Cell3D<>(coord, grid.get(coord).getValue().getAttributes()))
                     .collect(Collectors.toList());
    }

}
