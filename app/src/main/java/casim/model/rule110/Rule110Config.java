package casim.model.rule110;

/**
 * {@link casim.model.rule110.Rule110}
 * configuration class.
 */
public class Rule110Config {
    private final int maxRows;

    /**
     * Constructs a new {@link Rule110Config}
     * with given value.
     * 
     * @param maxRows the number of rows of the grid.
     */
    public Rule110Config(final int maxRows) {
        this.maxRows = maxRows;
    }

    /**
     * Return the number of rows in the grid.
     * 
     * @return return the number of rows in the grid.
     */
    public int getMaxRows() {
        return maxRows;
    }
}
