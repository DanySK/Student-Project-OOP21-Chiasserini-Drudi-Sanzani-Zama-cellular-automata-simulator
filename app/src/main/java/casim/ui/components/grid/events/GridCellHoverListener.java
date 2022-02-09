package casim.ui.grid.events;

import casim.ui.grid.CanvasGrid;
import javafx.scene.input.MouseEvent;

/**
 * The event listener used for the mouse hover event on the {@link CanvasGrid}.
 */
public class GridCellHoverListener extends AbstractMouseEventHandler {

    /**
     * Construct a new {@link GridCellHoverListener}.
     * 
     * @param grid the {@link CanvasGrid} the event has to work on.
     */
    public GridCellHoverListener(final CanvasGrid grid) {
        super(grid);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final MouseEvent event) {
        final var coord = this.getCoordinatesFromEvent(event);
        this.getCanvasGrid().getCell(coord)
            .ifPresent(cell -> this.getCanvasGrid().onCellHover(cell, coord));
    }
}
