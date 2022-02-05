package casim.ui.grid;

import casim.utils.coordinate.Coordinate;
import javafx.scene.paint.Color;

/**
 * Defines the {@link CanvasGrid} cell elements.
 */
public interface CanvasGridCell {

    /**
     * Return the {@link CanvasGridCell}'s color.
     * 
     * @return the {@link CanvasGridCell}'s' color.
     */
    Color getColor();

    /**
     * Sets the {@link CanvasGridCell} color.
     * 
     * @param color the new color.
     */
    void setColor(Color color);

    /**
     * Return the {@link CanvasGridCell}' top left point as {@link Coordinate}.
     * 
     * @return a {@link Coordinate} representing the top left point of the {@link CanvasGridCell}.
     */
    Coordinate<Integer> getTopLeft();

    /**
     * Return the {@link CanvasGridCell}' bottom right point as {@link Coordinate}.
     * 
     * @return a {@link Coordinate} representing the bottom right point of the {@link CanvasGridCell}.
     */
    Coordinate<Integer> getBottomRight();
}
