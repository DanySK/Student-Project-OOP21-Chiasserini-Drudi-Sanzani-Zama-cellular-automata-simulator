package casim.ui.grid;

import casim.utils.Grid;
import casim.utils.Result;
import casim.utils.coordinate.Coordinate;
import javafx.scene.input.MouseButton;

/**
 * Grid UI component that allows to capture mouse events. 
 */
public interface CanvasGrid {

    /**
     * Function called when a mouse button is clicked.
     * 
     * @param button the mouse's button that has been clicked.
     * @param cell the cell the mouse has clicked.
     * @param coord the coordinates of the cell.
     */
    void onCellClick(MouseButton button, CanvasGridCell cell, Coordinate<Integer> coord);

    /**
     * Function called when the mouse hovers the {@link CanvasGrid}.
     * 
     * @param cell the cell the mouse is hovering.
     * @param coord the coordinates of the cell.
     */
    void onCellHover(CanvasGridCell cell, Coordinate<Integer> coord);

    /**
     * Get the number of columns in the grid.
     * 
     * @return an integer representing the number of columns in the grid.
     */
    int getColumns();

    /**
     * Get the number of rows in the grid.
     * 
     * @return an integer representing the number of rows in the grid.
     */
    int getRows();

    /**
     * Populate the grid with cells.
     */
    void populate();

    /**
     * Draw the grid lines.
     */
    void drawLines();

    /**
     * Return the cell size.
     * 
     * @return a double representing the cell size.
     */
    double getCellSize();

    /**
     * Return the grid with all the cells.
     * 
     * @return a {@link Grid} containing the cells of the {@link CanvasGrid}.
     */
    Grid<CanvasGridCell> getCells();

    /**
     * Set the {@link CanvasGrid}'s grid.
     * 
     * @param cells a {@link Grid} containing the cells.
     */
    void setCells(Grid<CanvasGridCell> cells);

    /**
     * Get a specific cell of the canvas, returns a {@link Result} holding:
     *  - a {@link CanvasGridCell} if the coordinated are valid,
     *  - an IndexOutOfBoundException otherwise.
     * 
     * @param coord the {@link Coordinate} of the cell to get.
     * @return {@link Result} with the {@link CanvasGridCell} if coord is valid, IndexOutOfBoundException otherwise.
     */
    Result<CanvasGridCell> getCell(Coordinate<Integer> coord);
}
