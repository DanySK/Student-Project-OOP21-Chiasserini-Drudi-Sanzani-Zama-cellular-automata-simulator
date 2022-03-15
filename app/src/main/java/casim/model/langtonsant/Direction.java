package casim.model.langtonsant;

import java.util.List;

public enum Direction {
    NORTH(List.of(0, 1)),
    EAST(List.of(1, 0)),
    SOUTH(List.of(0, -1)),
    WEST(List.of(-1, 0));

    private List<Integer> moveInfo;

    private Direction(final List<Integer> moveInfo) {
        this.moveInfo = moveInfo;
    }

    /**
     * Given a {@link Direction} and a {@link CellState} returns the new altered {@link Direction}
     * 
     * @param direction current {@link Direction} to be altered
     * @param state current {@link CellState} of the {@link LangtonsAntCell} the {@link Ant} is on.
     * @return a new {@link Direction} altered based on the {@link CellState}
     */
    public static Direction turn(final Direction direction, final CellState state) {
        if (direction.equals(Direction.NORTH) && state.equals(CellState.ON)) {
            return Direction.WEST;
        }
        return Direction.values()[(direction.ordinal() + (state == CellState.ON ? -1 : +1)) % Direction.values().length];
    }

    /**
     * Returns the movement info of the {@link Direction}
     * @return a {@link List} of {@link Integer} describing the changes to be made to the X and Y coordinates
     * to move the {@link casim.model.langtonsant.Ant} in the correct {@link Direction}
     */
    public List<Integer> getMoveInfo() {
        return this.moveInfo;
    }
}
