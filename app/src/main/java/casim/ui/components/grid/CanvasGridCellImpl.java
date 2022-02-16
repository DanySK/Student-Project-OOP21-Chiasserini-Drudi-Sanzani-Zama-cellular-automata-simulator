package casim.ui.components.grid;

import casim.utils.coordinate.Coordinates2D;
import javafx.scene.paint.Color;

/**
 * A {@CanvasGridCell} implementation.
 */
public class CanvasGridCellImpl implements CanvasGridCell {

    private final Coordinates2D<Integer> topLeft;
    private final double size;
    private Color color;

    /**
     * Construct a {@link CanvasGridCellImpl}.
     * 
     * @param color the color of the cell.
     * @param topLeft the top left corner of the cell.
     * @param size the size of the cell.
     */
    public CanvasGridCellImpl(final Color color, final Coordinates2D<Integer> topLeft, final double size) {
        this.color = color;
        this.topLeft = topLeft;
        this.size = size;
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
    public void setColor(final Color color) {
        this.color = color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Coordinates2D<Integer> getTopLeft() {
        return this.topLeft;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSize() {
        return this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "CanvasGridCellImpl [color=" + color + ", size=" + size + ", topLeft=" + topLeft + "]";
    }
}
