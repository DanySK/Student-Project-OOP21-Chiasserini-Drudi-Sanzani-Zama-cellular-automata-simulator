package casim.model.utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.cell.AbstractCell;
import casim.utils.coordinate.Coordinates;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.Coordinates3D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid;

/**
 * Static class for the standard neighbors functions. 
 */
public final class NeighborsFunctions {

    private NeighborsFunctions() {
    }

    /**
     * Method to obtain all the neighbors of a 2D cell.
     * 
     * @param cellCoord the coordinates of the cell of which calculate the neighbors.
     * @param grid the grid where search for the neighbors.
     * @return an iterable containing all the neighbors of the cell.
     * @param <T> the enumeration which contains the finite states of the {@link casim.model.Automaton}'s {@link casim.model.Cell}.
     */
    public static <T extends Enum<T>> Iterable<Pair<Coordinates<Integer>, AbstractCell<T>>> neighbors2DFunction(final Coordinates<Integer> cellCoord, final Grid<Coordinates<Integer>, AbstractCell<T>> grid) {
         return Stream.of(CoordinatesUtil.of(1, 0), CoordinatesUtil.of(0, 1), CoordinatesUtil.of(0, -1), CoordinatesUtil.of(-1, 0))
                 .map(coord -> CoordinatesUtil.sumInt(coord, (Coordinates2D<Integer>) cellCoord))
                 .filter(grid::isCoordValid)
                 .map(coord -> Pair.of((Coordinates<Integer>) coord, grid.get(coord))) 
                 .collect(Collectors.toList());
    }

    /**
     * Method to obtain all the neighbors of a 3D cell.
     * 
     * @param cellCoord the coordinates of the cell of which calculate the neighbors.
     * @param grid the grid where search for the neighbors.
     * @return an iterable containing all the neighbors of the cell.
     * @param <T> the enumeration which contains the finite states of the {@link casim.model.Automaton}'s {@link casim.model.Cell}.
     */
    public static <T extends Enum<T>> Iterable<Pair<Coordinates<Integer>, AbstractCell<T>>> neighbors3DFunction(final Coordinates<Integer> cellCoord, final Grid<Coordinates<Integer>, AbstractCell<T>> grid) {
        return Stream.of(CoordinatesUtil.of(1, 0, 0), CoordinatesUtil.of(-1, 0, 0), CoordinatesUtil.of(0, 1, 0),
                CoordinatesUtil.of(0, -1, 0), CoordinatesUtil.of(0, 0, 1), CoordinatesUtil.of(0, 0, -1))
            .map(coord -> CoordinatesUtil.sumInt(coord, (Coordinates3D<Integer>) cellCoord))
            .filter(grid::isCoordValid)
            .map(coord -> Pair.of((Coordinates<Integer>) coord, grid.get(coord)))
            .collect(Collectors.toList());
    }

}
