package casim.model.codi;

/**
 * Enum containing all the neighbors directions.
 */
enum Direction {

    NORTH,
    SOUTH,
    WEST,
    EAST,
    TOP,
    BOTTOM;

    Direction getOpposite() {
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
