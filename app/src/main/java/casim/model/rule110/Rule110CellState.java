package casim.model.rule110;

/**
 * The state of {@link casim.model.rule110.Rule110Cell}.
 */
public enum Rule110CellState {
    ALIVE(1),
    DEAD(0);

    private final int value;

    /**
     * Method associating an int to its state.
     *  
     * @param value int to associate.
     */
    private Rule110CellState(final int value) {
        this.value = value;
    }

    /**
     * Get the associated int.
     * 
     * @return the associated value 
     */
    public int getValue() {
        return this.value;
    }
}
