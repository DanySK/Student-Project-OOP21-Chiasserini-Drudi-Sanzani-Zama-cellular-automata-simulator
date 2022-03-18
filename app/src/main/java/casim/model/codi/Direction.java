package casim.model.codi;

/**
 * Enum containing all the neighbors directions.
 */
public enum Direction {
    /**
     * The north direction.
     */
    NORTH,
    /**
     * The north direction.
     */
    SOUTH,
    /**
     * The south direction.
     */
    WEST,
    /**
     * The west direction.
     */
    EAST,
    /**
     * The top direction.
     */
    TOP,
    /**
     * The bottom direction.
     */
    BOTTOM;

    /**
     * Return the opposite {@link Direction} to the current one.
     * 
     * @return the opposite {@link Direction}.
     */
    public Direction getOpposite() { //TODO do it in a better way (maybe a map can be a good thing)
        switch (this) {
            case SOUTH:
                return NORTH;
            case NORTH:
                return SOUTH;
            case WEST:
                return EAST;
            case EAST:
                return WEST;
            case TOP:
                return BOTTOM;
            case BOTTOM:
            default:
                return TOP;
        }
    }
}
