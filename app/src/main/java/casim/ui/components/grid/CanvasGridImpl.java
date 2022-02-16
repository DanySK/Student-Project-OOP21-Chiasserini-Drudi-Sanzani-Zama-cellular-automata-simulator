package casim.ui.components.grid;

import java.util.Optional;

import casim.ui.components.grid.events.GridCellClickListener;
import casim.ui.components.grid.events.GridCellHoverListener;
import casim.utils.Colors;
import casim.utils.Result;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid2DImpl;
import casim.utils.range.Ranges;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

/**
 * Implementation of {@link CanvasGrid} to be used in the GUI.
 */
public class CanvasGridImpl extends Canvas implements CanvasGrid {

    private static final Color DEFAULT = Colors.WHITE;
    private static final Color SELECTED = Colors.RED;
    private static final Color HIGHLIGHTED = new Color(0.4, 0.4, 0.4, 1);

    private final int rows;
    private final int columns;
    private final double cellSize;
    private final Color separatorColor;
    private final double separatorWidth;
    private final double separatorOffset;
    private final double width;
    private final double height;

    private Optional<Coordinates2D<Integer>> lastHoveredCell;

    private final Grid2D<CanvasGridCell> cells;

    /**
     * Construct a new {@link CanvasGridImpl}.
     * 
     * @param rows number of rows of the grid (cells, not pixels).
     * @param columns number of columns of the grid (cells, not pixels).
     * @param cellSize cell size in pixels.
     * @param separatorColor separator color.
     * @param separatorWidth separator width.
     * @param separatorOffset separator offset.
     */
    public CanvasGridImpl(final int rows, final int columns, final double cellSize, 
        final Color separatorColor, final double separatorWidth, final double separatorOffset) {

        this.rows = rows;
        this.columns = columns;
        this.cellSize = cellSize;
        this.separatorColor = separatorColor;
        this.separatorWidth = separatorWidth;
        this.separatorOffset = separatorOffset;
        this.width = columns * cellSize;
        this.height = rows * cellSize;
        super.setWidth(width);
        super.setHeight(height);
        this.cells = new Grid2DImpl<>(rows, columns);
        this.lastHoveredCell = Optional.empty();
        this.setOnMouseClicked(new GridCellClickListener(this));
        this.setOnMouseMoved(new GridCellHoverListener(this));
        this.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCellClick(final MouseButton button, final CanvasGridCell cell, final Coordinates2D<Integer> coord) {
        if (!button.equals(MouseButton.PRIMARY)) {
            return;
        }
        cell.setColor(cell.getColor().equals(DEFAULT) ? SELECTED : DEFAULT);
        this.draw();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCellHover(final CanvasGridCell cell, final Coordinates2D<Integer> coord) {
        if (this.lastHoveredCell.equals(Optional.of(coord))) {
            return;
        }

        this.lastHoveredCell.ifPresent(
            lastCoord -> this.getCell(lastCoord).getValue().setColor(DEFAULT));

        cell.setColor(HIGHLIGHTED);
        this.lastHoveredCell = Optional.of(coord);
        this.draw();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumns() {
        return this.columns;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRows() {
        return this.rows;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        final var graphics = this.getGraphicsContext2D();
        this.drawCells(graphics);
        this.drawGrid(graphics);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCellSize() {
        return this.cellSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Grid2D<CanvasGridCell> getCells() {
        return null; //TODO: Grids.getUnmodifiableCopy(this.cells);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCells(final Grid2D<CanvasGridCell> cells) {
        //TODO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Result<CanvasGridCell> getCell(final Coordinates2D<Integer> coord) {
        return this.cells.get(coord.getX(), coord.getY());
    }

    private void init() {
        for (final var row : Ranges.of(0, this.rows)) {
            for (final var column : Ranges.of(0, this.columns)) {
                this.cells.set(row, column, new CanvasGridCellImpl(
                    DEFAULT,
                    CoordinatesUtil.of(row * (int) this.cellSize, column * (int) this.cellSize),
                    this.cellSize)
                );
            }
        }
    }

    private void drawCell(final GraphicsContext graphics, final CanvasGridCell cell) {
        graphics.setFill(cell.getColor());
        graphics.fillRect(
            cell.getTopLeft().getX(),
            cell.getTopLeft().getY(),
            this.getCellSize(),
            this.getCellSize()
        );
    }

    private void drawCells(final GraphicsContext graphics) {
        this.cells.stream()
            .forEach(cell -> this.drawCell(graphics, cell));
    }

    private void drawGrid(final GraphicsContext graphics) {
        if (this.separatorWidth == 0) {
            return;
        }
        graphics.setStroke(this.separatorColor);
        graphics.setLineWidth(this.separatorWidth);
        for (final var x : Ranges.of(0.0, this.width, this.getCellSize())) {
            graphics.strokeLine(x + this.separatorOffset, 0, x + this.separatorOffset, height);
        }
        for (final var y : Ranges.of(0.0, this.height, this.getCellSize())) {
            graphics.strokeLine(0, y + this.separatorOffset, width, y + this.separatorOffset);
        }
    }
}
