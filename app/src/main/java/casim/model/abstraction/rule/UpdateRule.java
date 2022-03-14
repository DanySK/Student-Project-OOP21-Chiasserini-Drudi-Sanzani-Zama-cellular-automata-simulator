package casim.model.abstraction.rule;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.cell.AbstractCell;
import casim.model.abstraction.cell.Cell;
import casim.utils.coordinate.Coordinates;
import casim.utils.grid.Grid;

/**
 * The {@link casim.model.abstraction.automaton.Automaton}'s rule used to update the {@link Cell}'s state.
 * 
 *  @param <T> the enumeration which contains the finite states of the {@link casim.model.abstraction.automaton.Automaton}'s {@link Cell}.
 */
public interface UpdateRule<C extends Coordinates<? extends Number>, T extends AbstractCell<?>> {

     /**
     * Return the updated {@link Cell}.
     * 
     * @param cellPair pair of {@link Coordinates} and {@link Cell} to update;
     * @param grid the {@link Grid} representing the {@link casim.model.abstraction.automaton.Automaton}.
     * @return the updated {@link Cell}.
     */
    T getNextCell(Pair<C, T> cellPair, Grid<C, T> grid);

}
