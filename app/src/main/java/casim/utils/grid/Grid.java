package casim.utils.grid;

import java.util.List;
import java.util.stream.Stream;

import casim.utils.Empty;
import casim.utils.Result;

/**
 * An N x M {@link Grid} of elements of type T.
 * 
 * @param <T> type of the elements contained in Grid.
 */
public interface Grid<T> {
    /**
     * Return the width of the {@link Grid}.
     * 
     * @return an integer representing the witdh of {@link Grid}.
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
     *  - the value contained in {@link Grid} if row and column are valid; 
     *  - an {@link IndexOutOfBoundsException} otherwise.
     * 
     * @param row of the element to get.
     * @param column of the element to get.
     * @return {@link Result} containing the requested element if present, {@link IndexOutOfBoundsException} otherwise.
     */
    Result<T> get(int row, int column);

    /**
     * Return a {@link Result} containing:
     *  - {@link Empty} if row and column are valid,
     *  - an {@link IndexOutOfBoundsException} otherwise.
     * 
     * @param row of the element to set.
     * @param column of the element to set.
     * @param value to set.
     * @return @return {@link Result} containing {@link Empty} if row and column are valid, {@link IndexOutOfBoundsException} otherwise.
     */
    Result<Empty> set(int row, int column, T value);

    /**
     * Return a Stream of the {@link Grid}'s rows.
     * 
     * @return Stream of the rows.
     */
    Stream<List<T>> stream();

    /**
     * Return a Stream of the elements in {@link Grid}.
     * 
     * @return a Stream of the elements in {@link Grid}.
     */
    Stream<T> flatStream();
}
