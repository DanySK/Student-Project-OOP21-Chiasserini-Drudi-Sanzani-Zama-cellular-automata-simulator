package casim.utils.grid;

import casim.utils.Empty;
import casim.utils.Result;
import casim.utils.coordinate.Coordinates3D;

/**
 * 3-Dimensional grid interface.
 * 
 * @param <T> the type contained in the {@link Grid3D}.
 */
public interface Grid3D<T> extends Grid<Coordinates3D<Integer>, T> {
    /**
     *Return the width of the {@link Grid}.
     *
     *@return an integer representing the width of {@link Grid}.
     */
    int getWidth();

    /**
     *Return the height of the {@link Grid}.
     *
     *@return an integer representing the height of {@link Grid}.
     */
    int getHeight();

    /**
     *Return the depth of the {@link Grid}.
     *
     *@return an integer representing the depth of {@link Grid}.
     */
    int getDepth();

    /**
     * Return a {@link Result} containing:
     *  - the value contained in {@link Grid} if row and column are valid;
     *  - an {@link IndexOutOfBoundsException} otherwise.
     * 
     * @param row of the element to get.
     * @param column of the element to get.
     * @param depth of the element to get.
     * @return {@link Result} containing the requested element if present, {@link IndexOutOfBoundsException} otherwise.
     */
    Result<T> get(int row, int column, int depth);

    /**
     * Return a {@link Result} containing:
     *  - {@link Empty} if row and column are valid;
     *  - an {@link IndexOutOfBoundsException} otherwise.
     * 
     * @param row of the element to set.
     * @param column of the element to set.
     * @param depth of the element to set.
     * @param value to set.
     * @return {@link Result} containing {@link Empty} if row, column and depth are valid, {@link IndexOutOfBoundsException} otherwise.
     */
    Result<Empty> set(int row, int column, int depth, T value);
}
