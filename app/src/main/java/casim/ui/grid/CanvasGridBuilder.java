package casim.ui.grid;

import java.util.Objects;

import casim.utils.Result;
import javafx.scene.paint.Color;

public class CanvasGridBuilder {

    private static final String INVALID_ROWS_NUMBER = "The number of rows must be greater than zero.";
    private static final String INVALID_COLUMNS_NUMBER = "The number of columns must be greater than zero.";
    private static final String INVALID_CELL_SIZE = "The cell size must be greater than zero.";
    private static final String COLOR_IS_NULL = "The separator color cannot be null.";
    private static final String INVALID_SEPARATOR_WIDTH = "The separator width must be greater than zero.";

    private int rows;
    private int columns;
    private int cellSize;
    private Color separatorColor;
    private double separatorWidth;
    
    public CanvasGridBuilder() {

    }

    public CanvasGridBuilder setRow(final int rows) {
        this.rows = rows;
        return this;
    }

    public CanvasGridBuilder setColumns(final int columns) {
        this.columns = columns;
        return this;
    }

    public CanvasGridBuilder setCellSize(final int cellSize) {
        this.cellSize = cellSize;
        return this;
    }

    public CanvasGridBuilder setSeparatorWidth(final double separatorWidth) {
        this.separatorWidth = separatorWidth;
        return this;
    }

    public CanvasGridBuilder setSeparatorColor(final Color separatorColor) {
        Objects.requireNonNull(separatorColor);
        this.separatorColor = separatorColor;
        return this;
    }

    public Result<CanvasGrid> build() {
        return Result.ofEmpty()
            .require(x -> this.rows > 0, () -> new IllegalArgumentException(INVALID_ROWS_NUMBER))
            .require(x -> this.columns > 0, () -> new IllegalArgumentException(INVALID_COLUMNS_NUMBER))
            .require(x -> this.cellSize > 0, () -> new IllegalArgumentException(INVALID_CELL_SIZE))
            .require(x -> this.separatorColor != null, () -> new NullPointerException(COLOR_IS_NULL))
            .require(x -> this.separatorWidth > 0.0, () -> new IllegalArgumentException(INVALID_SEPARATOR_WIDTH))
            .map(x -> new CanvasGridImpl(
                this.rows, 
                this.columns, 
                this.cellSize, 
                this.separatorColor, 
                this.separatorWidth, 
                this.getSeparatorOffset())
        );
    }

    private double getSeparatorOffset() {
        return this.separatorWidth / 2;
    }
}
