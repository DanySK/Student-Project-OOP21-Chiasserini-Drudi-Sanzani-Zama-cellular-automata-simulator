package casim.model.bryansbrain;

public class BryansBrainConfig {
    private final int rows;
    private final int cols;
    private final boolean wrapping;

    public BryansBrainConfig(final int rows, final int cols, final boolean wrapping) {
        this.rows = rows;
        this.cols = cols;
        this.wrapping = wrapping;
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public boolean isWrapped() {
        return this.wrapping;
    }
}
