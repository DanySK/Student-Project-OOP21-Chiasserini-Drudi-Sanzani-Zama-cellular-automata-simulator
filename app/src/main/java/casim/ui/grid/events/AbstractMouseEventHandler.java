package casim.ui.grid.events;

import casim.ui.grid.CanvasGrid;
import casim.utils.coordinate.Coordinates;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Abstract mouse event handler for {@link CanvasGrid}.
 */
public abstract class AbstractMouseEventHandler implements EventHandler<MouseEvent> {

    private final CanvasGrid grid;

    /**
     * Construct a new {@link AbstractMouseEventHandler} for {@link CanvasGrid}.
     * 
     * @param grid the {@link CanvasGrid} the event has to work on.
     */
    public AbstractMouseEventHandler(final CanvasGrid grid) {
        this.grid = grid;
    }

    /**
     * Get the cell {@link Coordinates} from the mouse event.
     * 
     * @param event the mouse event.
     * @return a {@link Coordinates} contaning the cell coordinates.
     */
    protected Coordinates<Integer> getCoordinatesFromEvent(final MouseEvent event) {
        return null; /*new CoordinatesImpl<Integer>(
            (int)(event.getX() / this.grid.getCellSize()), 
            (int)(event.getY() / this.grid.getCellSize())
        );*/
    }

    /**
     * Get the canvas grid.
     * 
     * @return the {@link CanvasGrid} related to the {@link AbstractMouseEventHandler}.
     */
    protected CanvasGrid getCanvasGrid() {
        return this.grid;
    }
}
