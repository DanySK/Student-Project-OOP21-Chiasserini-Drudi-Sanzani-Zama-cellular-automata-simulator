package casim.ui.grid.events;

import casim.ui.grid.CanvasGrid;
import javafx.scene.input.MouseEvent;

public class GridCellHoverListener extends AbstractMouseEventHandler {

    public GridCellHoverListener(final CanvasGrid grid) {
        super(grid);
    }

    @Override
    public void handle(final MouseEvent event) {
        final var coord = this.getCoordinatesFromEvent(event);
        this.grid.getCell(coord)
            .ifPresent(cell -> this.grid.onCellHover(cell, coord));
    }
}
