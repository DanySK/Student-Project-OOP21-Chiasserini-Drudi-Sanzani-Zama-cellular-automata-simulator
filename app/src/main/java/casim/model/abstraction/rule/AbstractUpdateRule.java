package casim.model.abstraction.rule;

import java.util.function.BiFunction;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.cell.AbstractCell;
import casim.model.abstraction.cell.Cell;
import casim.utils.coordinate.Coordinates;
import casim.utils.grid.Grid;

/**
 * Abstract implementation of {@link UpdateRule}.
 *
 *  @param <T> the enumeration which contains the finite states of the {@link casim.model.Automaton}'s {@link Cell}.
 */
public abstract class AbstractUpdateRule<T extends Enum<T>> implements UpdateRule<T> {

    private final BiFunction<Pair<Coordinates<Integer>, Cell<T>>, Grid<Coordinates<Integer>, Cell<T>>, Iterable<Pair<Coordinates<Integer>, Cell<T>>>> neighborsFunction;

    /**
     * Constructor of an abstract updateRule.
     * 
     * @param neighborsFunction used to take the neighbors of a given cell.
     */
    public AbstractUpdateRule(final BiFunction<Pair<Coordinates<Integer>, Cell<T>>, Grid<Coordinates<Integer>, Cell<T>>, Iterable<Pair<Coordinates<Integer>, Cell<T>>>> neighborsFunction) {
        this.neighborsFunction = neighborsFunction;
    }

    @Override
    public AbstractCell<T> getNextCell(final Pair<Coordinates<Integer>, Cell<T>> cellPair, final Grid<Coordinates<Integer>, Cell<T>> grid) {
        return this.nextCell(cellPair, this.neighborsFunction.apply(cellPair, grid));
    }

    /**
     * Abstract method used to calculate the updated the cell of the one taken as input. 
     * 
     * @param cellPair pair of {@link Coordinates} and {@link Cell} to updates;
     * @param neighborsPairs an iterable containing all the pair describing all the pairs {@link Coordinates} + {@link Cell} neighbors of the cellPair.
     * @return the new updated {@link Cell}.
     */
    protected abstract AbstractCell<T> nextCell(Pair<Coordinates<Integer>, Cell<T>> cellPair, Iterable<Pair<Coordinates<Integer>, Cell<T>>> neighborsPairs);
}
