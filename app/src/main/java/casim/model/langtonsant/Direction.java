package casim.model.langtonsant;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    /**
     * Given a {@link Direction} and a {@link CellState} returns the new altered {@link Direction}
     * 
     * @param direction current {@link Direction} to be altered
     * @param state current {@link CellState} of the {@link LangtonsAntCell} the {@link Ant} is on.
     * @return a new {@link Direction} altered based on the {@link CellState}
     */
    public static Direction turn(final Direction direction, final CellState state) {
        return Direction.values()[direction.ordinal() + (state == CellState.ON ? -1 : +1) % Direction.values().length];
    }
}
