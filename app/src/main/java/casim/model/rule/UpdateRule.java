package casim.model.rule;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.cell.Cell;
import casim.utils.coordinate.Coordinates;
import casim.utils.grid.Grid;

/**
 * The {@link Automaton}'s rule used to update the {@link Cell}'s state.
 * 
 *  @param <T> the type of the finite states of the {@link Automaton}'s {@link Cell}.
 */
public interface UpdateRule<T> {

     /**
     * Return the updated {@link Cell}.
     * 
     * @param cellPair pair of {@link Coordinates} and {@link Cell} to updates;
     * @param grid the {@link Grid} representing the {@link Automaton}.
     * @return the updated {@link Cell}.
     */
    Cell<T> getNextCell(Pair<Coordinates<Integer>, Cell<T>> cellPair, Grid<Coordinates<Integer>, Cell<T>> grid);

}
