package casim.ui.components.grid;

import javafx.scene.paint.Color;

public interface CanvasGridBuilder {
    /**
     * Set the {@link CanvasGrid} separator width in pixels.
     *
     * @param separatorWidth the separator width.
     * @return a {@link CanvasGridBuilderImpl}.
     */
    public CanvasGridBuilderImpl separatorWidth(final double separatorWidth);

    /**
     * Set the {@link CanvasGrid} separator color.
     * 
     * @param separatorColor
     * @return a {@link CanvasGridBuilderImpl}.
     */
    public CanvasGridBuilderImpl separatorColor(final Color separatorColor);

    /**
     * Build the {@link CanvasGrid} object, setting the mandatory fields.
     *  
     * Throws an IllegalArgumentException if the values are invalid.
     * 
     * @return a {@link CanvasGrid} if the parameters are valid.
     */
    public CanvasGrid build(int rows, int columns, int cellSize);
}
