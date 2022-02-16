package casim.model.rule;

import java.util.function.Function;

import casim.model.cell.interfaces.Cell;
import casim.utils.grid.Grid;

/**
 * Abstract implementation of {@link UpdateRule}.
 *
 * @param <T> the type of the finite states which the {@link Cell} can assume.
 */
public abstract class AbstractUpdateRule<T> implements UpdateRule<T> {

    private final Function<Cell<T>, Iterable<Cell<T>>> neighborsFunction;

    /**
     * Constructor of an abstract updateRule.
     * 
     * @param neighborsFunction used to take the neighbors of a given cell.
     */
    public AbstractUpdateRule(final Function<Cell<T>, Iterable<Cell<T>>> neighborsFunction) {
        this.neighborsFunction = neighborsFunction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cell<T> getNextCell(final Cell<T> cell, final Grid<Cell<T>> grid) {
        final Iterable<Cell<T>> neighbors = this.neighborsFunction.apply(cell);
        return this.nextCell(cell, neighbors);
    }

    /**
     * Abstract method used to calculate the updated the cell of the one taken as input. 
     * 
     * @param cell the cell to update (the updated value is returned by this method).
     * @param neighbors an iterable containing all the neighbors of cell.
     * @return the new updated {@link Cell}.
     */
    protected abstract Cell<T> nextCell(Cell<T> cell, Iterable<Cell<T>> neighbors);
}
