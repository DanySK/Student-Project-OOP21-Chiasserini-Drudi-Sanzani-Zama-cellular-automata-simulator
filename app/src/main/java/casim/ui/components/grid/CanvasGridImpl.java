package casim.ui.components.grid;

import java.util.ArrayDeque;
import java.util.Queue;

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
    private final int rows;
    private final int columns;
    private final double cellSize;
    private final Color separatorColor;
    private final double separatorWidth;
    private final double separatorOffset;
    private final double width;
    private final double height;

    private final Grid2D<CanvasGridCell> cells;
    private final Queue<Coordinates2D<Integer>> renderQueue;

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
        this.renderQueue = new ArrayDeque<>();
    }

    @Override
    public void onCellClick(MouseButton button, CanvasGridCell cell, Coordinates2D<Integer> coord) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onCellHover(CanvasGridCell cell, Coordinates2D<Integer> coord) {
        // TODO Auto-generated method stub
        
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
    public void setCells(final Grid2D<Color> cellColors) {
        for (final var row : Ranges.of(0, this.getRows())) {
            for (final var column : Ranges.of(0, this.getColumns())) {
                final var coords = CoordinatesUtil.of(row, column);
                final var current = this.cells.get(coords);
                if (current != null && current.getColor().equals(cellColors.get(coords))) {
                    continue;
                }
                final var topLeft = CoordinatesUtil.of(row * ((int) this.cellSize), column * ((int) this.cellSize));
                final var cell = new CanvasGridCellImpl(
                    cellColors.get(coords),
                    topLeft,
                    this.cellSize);
                this.cells.set(coords, cell);
                this.renderQueue.add(coords);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CanvasGridCell getCell(final Coordinates2D<Integer> coord) {
        return this.cells.get(coord);
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
        System.out.println("Queue size: " + this.renderQueue.size());
        this.renderQueue.stream()
            .forEach(coord -> this.drawCell(graphics, this.cells.get(coord)));
        this.renderQueue.clear();
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
