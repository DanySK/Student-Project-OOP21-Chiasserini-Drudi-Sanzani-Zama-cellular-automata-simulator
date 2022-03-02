package casim.ui.components.grid;

import casim.utils.coordinate.Coordinates2D;
import casim.utils.grid.Grid2D;
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
    void onCellClick(MouseButton button, CanvasGridCell cell, Coordinates2D<Integer> coord);

    /**
     * Function called when the mouse hovers the {@link CanvasGrid}.
     * 
     * @param cell the cell the mouse is hovering.
     * @param coord the coordinates of the cell.
     */
    void onCellHover(CanvasGridCell cell, Coordinates2D<Integer> coord);

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
     * Draw the grid.
     */
    void draw();

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
    Grid2D<CanvasGridCell> getCells();

    /**
     * Set the {@link CanvasGrid}'s grid.
     * 
     * @param cells a {@link Grid} containing the cells.
     */
    void setCells(Grid2D<CanvasGridCell> cells);

    /**
     * Get a specific cell of the canvas.
     * 
     * @param coord the {@link Coordinates2D} of the cell to get.
     * @return the {@link CanvasGrid} of coordinates coord.
     */
    CanvasGridCell getCell(Coordinates2D<Integer> coord);
}
