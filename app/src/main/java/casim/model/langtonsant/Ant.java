package casim.model.langtonsant;

/**
 * An ant contained in a {@link casim.model.langtonsant.LangtonsAntCell}.
 */
public class Ant {
    private Direction direction;

    public Ant(final Direction direction) {
        this.direction = direction;
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
}
