package casim.model.abstraction.rule;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.cell.AbstractCell;
import casim.utils.coordinate.Coordinates;
import casim.utils.grid.Grid;

/**
 * The {@link casim.model.abstraction.automaton.Automaton}'s rule used to update the {@link casim.model.abstraction.cell.Cell}'s state.
 * 
 *  @param <T> the {@link AbstractCell} implementation to update.
 *  @param <C> the {@link Coordinates} implementation used by the cell (it can be 2D or 3D).
 */
public interface UpdateRule<C extends Coordinates<? extends Number>, T extends AbstractCell<?>> {

     /**
     * Return the updated {@link casim.model.abstraction.cell.Cell}.
     * 
     * @param cellPair pair of {@link Coordinates} and {@link Cell} to update;
     * @param grid the {@link Grid} representing the {@link Automaton}.
     * @return the updated {@link Cell}.
     */
    T getNextCell(Pair<C, T> cellPair, Grid<C, T> grid);

}
