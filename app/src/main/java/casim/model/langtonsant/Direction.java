package casim.model.langtonsant;

import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.CoordinatesUtil;

public enum Direction {
    
    NORTH(CoordinatesUtil.of(0, 1)),
    EAST(CoordinatesUtil.of(1, 0)),
    SOUTH(CoordinatesUtil.of(0, -1)),
    WEST(CoordinatesUtil.of(-1, 0));

    private Coordinates2D<Integer> moveInfo;

    private Direction(final Coordinates2D<Integer> moveInfo) {
        this.moveInfo = moveInfo;
    }

    /**
     * Returns the movement info of the {@link Direction}
     * @return a {@link Coordinates2D} of {@link Integer} describing the changes to be made to the X and Y coordinates
     * to move the {@link casim.model.langtonsant.Ant} in the correct {@link Direction}
     */
    public Coordinates2D<Integer> getMoveInfo() {
        return this.moveInfo;
    }
}
