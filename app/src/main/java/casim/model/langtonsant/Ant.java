package casim.model.langtonsant;

import casim.utils.coordinate.Coordinates2D;

/**
 * An ant contained in a {@link casim.model.langtonsant.LangtonsAntCell}.
 */
public class Ant {

    private Coordinates2D<Integer> position;
    private Direction direction;

    public Ant(final Direction direction, final Coordinates2D<Integer> position) {
        this.direction = direction;
        this.position = position;
    }

    /**
     * Returns the current direction of the {@link Ant}
     * @return the current {@link Direction} of the {@link Ant}
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Sets the current {@link Direction} of the {@link Ant}
     * @param direction the new {@link Direction} to be set.
     */
    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    public Coordinates2D<Integer> getPosition() {
        return this.position;
    }

    public void setPosition(final Coordinates2D<Integer> position) {
        this.position = position;
    }
}
