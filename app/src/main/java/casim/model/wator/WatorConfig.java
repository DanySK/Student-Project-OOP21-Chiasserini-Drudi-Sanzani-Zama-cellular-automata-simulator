package casim.model.wator;

/**
 * {@link casim.model.wator.Wator}
 * configuration class.
 */
public class WatorConfig {

    private final int rows;
    private final int cols;
    
    /**
     * Constructs a new {@link WatorConfig}
     * with given values.
     * 
     * @param rows the number of rows of the grid.
     * @param cols the number of columns of the grid.
     */
    public WatorConfig(final int rows, final int cols) {
        this.rows = rows;
        this.cols = cols;
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
}
