package casim.ui.components.grid;

import casim.utils.AbstractBuilder;
import casim.utils.Colors;
import javafx.scene.paint.Color;

/**
 * Builder class for {@link CanvasGrid}.
 */
public class CanvasGridBuilderImpl extends AbstractBuilder implements CanvasGridBuilder {

    private static final String INVALID_ROWS_NUMBER = "The number of rows must be greater than zero.";
    private static final String INVALID_COLUMNS_NUMBER = "The number of columns must be greater than zero.";
    private static final String INVALID_CELL_SIZE = "The cell size must be greater than zero.";
    private static final String INVALID_SEPARATOR_WIDTH = "The separator width must be equal or greater than zero.";
    private static final String SEPARATOR_COLOR_NULL = "The separator color cannot be null.";

    private int rows;
    private int columns;
    private int cellSize;
    private Color separatorColor = Colors.BLACK;
    private double separatorWidth;

    @Override
    public CanvasGridBuilderImpl separatorWidth(final double separatorWidth) {
        this.registerCall();
        this.separatorWidth = this.checkValue(separatorWidth, x -> x >= 0, INVALID_SEPARATOR_WIDTH);
        return this;
    }

    @Override
    public CanvasGridBuilderImpl separatorColor(final Color separatorColor) {
        this.registerCall();
        this.separatorColor = this.checkNonNullValue(separatorColor, SEPARATOR_COLOR_NULL);
        return this;
    }

    @Override
    public CanvasGrid build(int rows, int columns, int cellSize) {
        this.registerCall();
        this.rows(rows)
            .columns(columns)
            .cellSize(cellSize);
        return new CanvasGridImpl(
            this.rows, 
            this.columns, 
            this.cellSize, 
            this.separatorColor, 
            this.separatorWidth, 
            this.getSeparatorOffset());
    }

    private double getSeparatorOffset() {
        return this.separatorWidth / 2;
    }

    private CanvasGridBuilderImpl rows(final int rows) {
        this.registerCall();
        this.rows = this.checkValue(rows, x -> x > 0, INVALID_ROWS_NUMBER);
        return this;
    }

    private CanvasGridBuilderImpl columns(final int columns) {
        this.registerCall();
        this.columns = this.checkValue(rows, x -> x > 0, INVALID_COLUMNS_NUMBER);
        return this;
    }

    private CanvasGridBuilderImpl cellSize(final int cellSize) {
        this.registerCall();
        this.cellSize = this.checkValue(rows, x -> x > 0, INVALID_CELL_SIZE);
        return this;
    }
}
