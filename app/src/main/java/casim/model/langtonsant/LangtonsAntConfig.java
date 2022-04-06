package casim.model.langtonsant;

/**
 * {@link casim.model.langtonsant.LangtonsAnt}
 * configuration class.
 */
public class LangtonsAntConfig {

    private final int rows;
    private final int cols;
    private final boolean wrapping;
    private final int antNumber;

    /**
     * Constucts a new {@link LangtonsAntConfig}
     * with given values.
     * 
     * @param rows the number of rows of the grid.
     * @param cols the number of colums of the grid.
     * @param wrapping true if the grid is a wrapping grid.
     * @param antNumber the number of ants in the automaton.
     */
    public LangtonsAntConfig(final int rows, final int cols, final boolean wrapping, final int antNumber) {
        this.rows = rows;
        this.cols = cols;
        this.wrapping = wrapping;
        this.antNumber = antNumber;
    }

    /**
     * Returns the number of rows.
     * 
     * @return the number of rows of the grid.
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * Returns the number of columns.
     * 
     * @return the number of columns of the grid.
     */
    public int getCols() {
        return this.cols;
    }

    /**
     * Returns the wrapping value.
     * 
     * @return true if the grid is a wrapping
     *          grid, false otherwise.
     */
    public boolean isWrapped() {
        return this.wrapping;
    }

    /**
     * Returns the number of ants in the
     * automaton.
     * 
     * @return the number of ants in the
     *          automaton.
     */
    public int getAntNumber() {
        return this.antNumber;
    }
}
