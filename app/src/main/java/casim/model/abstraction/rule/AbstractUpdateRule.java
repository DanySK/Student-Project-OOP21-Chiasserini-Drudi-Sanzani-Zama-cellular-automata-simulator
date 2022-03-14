package casim.model.abstraction.rule;

import java.util.List;
import java.util.function.BiFunction;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.cell.AbstractCell;
import casim.model.abstraction.cell.Cell;
import casim.utils.coordinate.Coordinates;
import casim.utils.grid.Grid;

/**
 * Abstract implementation of {@link UpdateRule}.
 *
 *  @param <T> the enumeration which contains the finite states of the {@link casim.model.abstraction.automaton.Automaton}'s {@link Cell}.
 */
public abstract class AbstractUpdateRule<C extends Coordinates<? extends Number>, T extends AbstractCell<?>> implements UpdateRule<C, T> {

    private final BiFunction<Pair<C, T>, Grid<C, T>, List<Pair<C, T>>> neighborsFunction;

    /**
     * Constructor of an abstract updateRule.
     * 
     * @param neighborsFunction used to take the neighbors of a given cell.
     */
    public AbstractUpdateRule(final BiFunction<Pair<C, T>, Grid<C, T>, List<Pair<C, T>>> neighborsFunction) {
        this.neighborsFunction = neighborsFunction;
    }

    @Override
    public T getNextCell(final Pair<C, T> cellPair, final Grid<C, T> grid) {
        return this.nextCell(cellPair, this.neighborsFunction.apply(cellPair, grid));
    }

    /**
     * Abstract method used to calculate the updated the cell of the one taken as input. 
     * 
     * @param cellPair pair of {@link Coordinates} and {@link Cell} to updates;
     * @param neighborsPairs an iterable containing all the pair describing all the pairs {@link Coordinates} + {@link Cell} neighbors of the cellPair.
     * @return the new updated {@link Cell}.
     */
    protected abstract T nextCell(Pair<C, T> cellPair, List<Pair<C, T>> neighborsPairs);
}
