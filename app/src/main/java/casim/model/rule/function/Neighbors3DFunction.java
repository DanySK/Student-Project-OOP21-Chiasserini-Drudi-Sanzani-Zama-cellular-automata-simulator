package casim.model.rule.function;

import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.cell.Cell;
import casim.utils.coordinate.Coordinates3D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid3D;

/**
 * General function used to get the neighbors of a {@link Cell} in a {@link Grid3D}.
 * 
 *  @param <T> the type of the finite states of the {@link Automaton}'s {@link Cell}.
 */
public class Neighbors3DFunction<T> implements BiFunction<Pair<Coordinates3D<Integer>, Cell<T>>, Grid3D<Cell<T>>, Iterable<Pair<Coordinates3D<Integer>, Cell<T>>>> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Pair<Coordinates3D<Integer>, Cell<T>>> apply(final Pair<Coordinates3D<Integer>, Cell<T>> cellPair, final Grid3D<Cell<T>> grid) {
        return Stream.of(CoordinatesUtil.of(1, 0, 0), CoordinatesUtil.of(-1, 0, 0), CoordinatesUtil.of(0, 1, 0),
                CoordinatesUtil.of(0, -1, 0), CoordinatesUtil.of(0, 0, 1), CoordinatesUtil.of(0, 0, -1))
            .map(coord -> CoordinatesUtil.sumInt(coord, cellPair.getLeft()))
            .filter(grid::isCoordValid)
            .map(coord -> Pair.of(coord, grid.get(coord)))
            .collect(Collectors.toList());
    }

}
