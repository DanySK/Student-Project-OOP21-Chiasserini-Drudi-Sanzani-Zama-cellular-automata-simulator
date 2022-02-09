package casim.ui.grid;

import java.util.Objects;

import casim.utils.Colors;
import casim.utils.Result;
import javafx.scene.paint.Color;

/**
 * Builder class for {@link CanvasGrid}.
 */
public class CanvasGridBuilder {

    private static final String INVALID_ROWS_NUMBER = "The number of rows must be greater than zero.";
    private static final String INVALID_COLUMNS_NUMBER = "The number of columns must be greater than zero.";
    private static final String INVALID_CELL_SIZE = "The cell size must be greater than zero.";
    private static final String INVALID_SEPARATOR_WIDTH = "The separator width must be equal or greater than zero.";

    private int rows = -1;
    private int columns = -1;
    private int cellSize = -1;
    private Color separatorColor = Colors.BLACK;
    private double separatorWidth = 0.0;
    
    /**
     * Construct a {@link CanvasGridBuilder}.
     */
    public CanvasGridBuilder() {

    }

    /**
     * Set the number of rows in number of cells of the {@link CanvasGrid}.
     * 
     * @param rows the number of rows.
     * @return a {@link CanvasGridBuilder}.
     */
    public CanvasGridBuilder setRows(final int rows) {
        this.rows = rows;
        return this;
    }

    /**
     * Set the number of columns in number of cells of the {@link CanvasGrid}.
     * 
     * @param columns the number of columns.
     * @return a {@link CanvasGridBuilder}.
     */
    public CanvasGridBuilder setColumns(final int columns) {
        this.columns = columns;
        return this;
    }

    /**
     * Set the cell size in pixels.
     * 
     * @param cellSize the cells size.
     * @return a {@link CanvasGridBuilder}.
     */
    public CanvasGridBuilder setCellSize(final int cellSize) {
        this.cellSize = cellSize;
        return this;
    }

    /**
     * Set the {@link CanvasGrid} separator width in pixels.
     *
     * @param separatorWidth the separator width.
     * @return a {@link CanvasGridBuilder}.
     */
    public CanvasGridBuilder setSeparatorWidth(final double separatorWidth) {
        this.separatorWidth = separatorWidth;
        return this;
    }

    /**
     * Set the {@link CanvasGrid} separator color.
     * 
     * @param separatorColor
     * @return a {@link CanvasGridBuilder}.
     */
    public CanvasGridBuilder setSeparatorColor(final Color separatorColor) {
        Objects.requireNonNull(separatorColor);
        this.separatorColor = separatorColor;
        return this;
    }

    /**
     * Build the {@link CanvasGrid} object, the result is wrapped in a {@link Result} holding:
     *  - the {@link CanvasGrid} if the given parameters are valid.
     *  - an exception otherwise.
     * 
     * 
     * @return a {@link Result} holding {@link CanvasGrid} if the parameters are valid, an exception otherwise.
     */
    public Result<CanvasGrid> build() {
        return Result.ofEmpty()
            .require(x -> this.rows > 0, () -> new IllegalArgumentException(INVALID_ROWS_NUMBER))
            .require(x -> this.columns > 0, () -> new IllegalArgumentException(INVALID_COLUMNS_NUMBER))
            .require(x -> this.cellSize > 0, () -> new IllegalArgumentException(INVALID_CELL_SIZE))
            .require(x -> this.separatorWidth >= 0.0, () -> new IllegalArgumentException(INVALID_SEPARATOR_WIDTH))
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
