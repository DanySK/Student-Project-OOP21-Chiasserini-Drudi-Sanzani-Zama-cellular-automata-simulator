package casim.ui.grid;

import casim.utils.coordinate.Coordinate;
import javafx.scene.paint.Color;

/**
 * A {@CanvasGridCell} implementation.
 */
public class CanvasGridCellImpl implements CanvasGridCell {

    private final Coordinate<Integer> topLeft;
    private final Coordinate<Integer> bottomRight;
    private Color color;

    /**
     * Construct a {@link CanvasGridCellImpl}.
     * 
     * @param color the color of the cell.
     * @param topLeft the top left corner of the cell.
     * @param bottomRight the bottom right corner of the cell.
     */
    public CanvasGridCellImpl(final Color color, final Coordinate<Integer> topLeft,
        final Coordinate<Integer> bottomRight) {
        this.color = color;
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Coordinate<Integer> getTopLeft() {
        return this.topLeft;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Coordinate<Integer> getBottomRight() {
        return this.bottomRight;
    }
}
