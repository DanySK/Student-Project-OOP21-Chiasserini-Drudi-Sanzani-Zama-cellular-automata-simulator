package casim.model.rule110;

public enum Rule110CellState {
    ALIVE(1),
    DEAD(0);

    private final int value;

    private Rule110CellState(final int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
