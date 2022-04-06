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
}
