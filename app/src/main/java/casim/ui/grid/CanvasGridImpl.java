package casim.ui.grid;

import java.util.stream.Stream;
import casim.ui.grid.events.GridCellClickListener;
import casim.ui.grid.events.GridCellHoverListener;
import casim.utils.Colors;
import casim.utils.Result;
import casim.utils.coordinate.Coordinates;
import casim.utils.grid.Grid;
import casim.utils.grid.Grids;
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

    private final int rows;
    private final int columns;
    private final double cellSize;
    private final Color separatorColor;
    private final double separatorWidth;
    private final double separatorOffset;
    private final double width;
    private final double height;

    private Grid<CanvasGridCell> cells;

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

        this.cells = null; //new GridImpl<CanvasGridCell>(columns, rows, () -> null); //TODO

        this.populate();

        this.setOnMouseClicked(new GridCellClickListener(this));
        this.setOnMouseMoved(new GridCellHoverListener(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCellClick(final MouseButton button, final CanvasGridCell cell, final Coordinates<Integer> coord) {
        System.out.println(coord.getX() + " " + coord.getY());
        // if (!button.equals(MouseButton.PRIMARY)) {
        //     return;
        // }
        // cell.setColor(cell.getColor().equals(DEFAULT) ? SELECTED : DEFAULT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCellHover(final CanvasGridCell cell, final Coordinates<Integer> coord) {
        System.out.println(coord.getX() + " " + coord.getY());
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
    public void populate() {
        /*Stream.iterate(0, row -> row + 1)
            .limit(this.getRows())
            .forEach(
                row -> Stream.iterate(0, col -> col + 1)
                    .limit(this.getColumns())
                    .forEach(col -> {
                        this.cells.set(row, col, new CanvasGridCellImpl(
                            DEFAULT,
                            new CoordinatesImpl<>(row * (int)this.getCellSize(), col * (int)this.getCellSize()),
                            new CoordinatesImpl<>((row + 1) * (int)this.getCellSize() , (col + 1) * (int)this.getCellSize())));
                        })
            );
        */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        final var graphics = this.getGraphicsContext2D();
        this.drawGrid(graphics);
        this.drawCells(graphics);
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
    public Grid<CanvasGridCell> getCells() {
        return Grids.getUnmodifiableCopy(this.cells);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCells(final Grid<CanvasGridCell> cells) {
        //TODO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Result<CanvasGridCell> getCell(final Coordinates<Integer> coord) {
        return Result.error(new Exception());
        // return this.cells.get(coord.getX(), coord.getY());
    }

    private void drawCells(final GraphicsContext graphics) {
        return;
        /*
        this.cells.flatStream()
            .forEach(cell -> {
                graphics.setFill(cell.getColor());
                graphics.fillRect(
                    cell.getTopLeft().getX(),
                    cell.getTopLeft().getY(),
                    cell.getBottomRight().getX(),
                    cell.getBottomRight().getY()
                );
            });*/
    }

    private void drawGrid(final GraphicsContext graphics) {
        graphics.setStroke(this.separatorColor);
        graphics.setLineWidth(this.separatorWidth);
        Stream.iterate(0.0, x -> x + this.getCellSize())
            .takeWhile(x -> x <= this.width)
            .forEach(x -> graphics.strokeLine(x + this.separatorOffset, 0, x + this.separatorOffset, height));
        Stream.iterate(0.0, y -> y + this.getCellSize())
            .takeWhile(y -> y <= this.height)
            .forEach(y -> graphics.strokeLine(0, y + this.separatorOffset, width, y + this.separatorOffset));
    }
}
