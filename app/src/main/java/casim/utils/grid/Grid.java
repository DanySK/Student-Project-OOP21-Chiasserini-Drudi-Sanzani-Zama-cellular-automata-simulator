package casim.utils.grid;

import java.util.stream.Stream;

import casim.utils.Empty;
import casim.utils.Result;
import casim.utils.coordinate.Coordinates;

/**
 * An N x M {@link Grid} of elements of type T.
 * 
 * @param <K> type of the {@link Coordinates} contained in Grid.
 * @param <V> type of the elements contained in Grid
 */
public interface Grid<K extends Coordinates<? extends Number>, V> {
    /**
     * Return the width of the {@link Grid}.
     * 
     * @return an integer representing the width of {@link Grid}.
     */
    int getWidth();

    /**
     * Return the height of the {@link Grid}.
     * 
     * @return an integer representing the height of {@link Grid}.
     */
    int getHeight();

    /**
     * Return a {@link Result} containing:
     *  - the value contained in {@link Grid} if the {@link Coordinates} are valid; 
     *  - an {@link IndexOutOfBoundsException} otherwise.
     * 
     * @param coord the {@link Coordinates} of the point.
     * @return {@link Result} containing the requested element if present, {@link IndexOutOfBoundsException} otherwise.
     */
    Result<V> get(K coord);

    /**
     * Return a {@link Result} containing:
     *  - {@link Empty} if the {@link Coordinates} are valid,
     *  - an {@link IndexOutOfBoundsException} otherwise.
     * 
     * @param coord the coordinates of the element to set.
     * @param value to set.
     * @return @return {@link Result} containing {@link Empty} if the {@link Coordinates} are valid, {@link IndexOutOfBoundsException} otherwise.
     */
    Result<Empty> set(K coord, V value);
    
    /**
     * Return true if parameter coord is inside the {@link Grid}.
     * 
     * @param coord the {@link Coordinates} of the point.
     * @return.
     */
    boolean isCoordValid(K coord);

    /**
     * Return a Stream of the elements in {@link Grid}.
     * 
     * @return a Stream of the elements in {@link Grid}.
     */
    Stream<V> stream();

}
