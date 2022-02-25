package casim.model.rule.function;

import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.cell.Cell;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid2D;

/**
 * General function used to calculate neighbors of a {@link Cell} in a 2D grid.
 * 
 * @param <T> the type of the finite states a {@link Cell} can assume.
 */
public class Neighbors2DFunction<T> implements BiFunction<Pair<Coordinates2D<Integer>, Cell<T>>, Grid2D<Cell<T>>, Iterable<Pair<Coordinates2D<Integer>, Cell<T>>>> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Pair<Coordinates2D<Integer>, Cell<T>>> apply(final Pair<Coordinates2D<Integer>, Cell<T>> cellPair, final Grid2D<Cell<T>> grid) {
        return Stream.of(CoordinatesUtil.of(1, 0), CoordinatesUtil.of(0, 1), CoordinatesUtil.of(0, -1), CoordinatesUtil.of(-1, 0))
            .map(coord -> CoordinatesUtil.sumInt(coord, cellPair.getLeft()))
            .filter(grid::isCoordValid)
            .map(coord -> Pair.of(coord, grid.get(coord))) 
            .collect(Collectors.toList());
    }

}
