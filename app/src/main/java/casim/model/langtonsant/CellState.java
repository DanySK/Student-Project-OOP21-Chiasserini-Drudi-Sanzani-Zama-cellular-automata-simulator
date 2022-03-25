package casim.model.langtonsant;

/**
 * The state of a {@link casim.model.langtonsant.Ant}.
 */
public enum CellState {
    /**
     * The activated state, an ant on this state
     * will turn left.
     */
    ON,
    /**
     * The deactivated state, an ant on this state
     * will turn right.
     */
    OFF;
}
