package casim.model.rule.function;

import java.util.function.Function;
import casim.model.cell.interfaces.Cell;

/**
 * General function used to calculate neighbors of a {@link Cell} in a 2D grid.
 * 
 * @param <T> the type of the finite states a {@link Cell} can assume.
 */
public class Neighbors2DFunction<T> implements Function<Cell<T>, Iterable<Cell<T>>> {

    @Override
    public Iterable<Cell<T>> apply(final Cell<T> t) {
        // TODO Auto-generated method stub
        return null;
    }

}
