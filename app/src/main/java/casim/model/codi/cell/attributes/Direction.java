package casim.model.codi.cell.attributes;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

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

    private static final Map<Direction, Direction> OPPOSITE_DIRECTION = new EnumMap<>(Direction.class);

    static {
        OPPOSITE_DIRECTION.put(NORTH, SOUTH);
        OPPOSITE_DIRECTION.put(SOUTH, NORTH);
        OPPOSITE_DIRECTION.put(WEST, EAST);
        OPPOSITE_DIRECTION.put(EAST, WEST);
        OPPOSITE_DIRECTION.put(TOP, BOTTOM);
        OPPOSITE_DIRECTION.put(BOTTOM, TOP);

    }

    /**
     * Return the opposite {@link Direction} to the current one.
     * 
     * @return the opposite {@link Direction}.
     */
    public Direction getOpposite() { 
        return OPPOSITE_DIRECTION.get(this);
    }

    /**
     * Return a random {@link Direction}.
     * 
     * @return a random {@link Direction}.
     */
    public static Direction getRandomDirection() {
        final Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
