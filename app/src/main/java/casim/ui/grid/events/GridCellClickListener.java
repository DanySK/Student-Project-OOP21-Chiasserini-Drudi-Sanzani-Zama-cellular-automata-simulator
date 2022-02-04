package casim.ui.grid.events;

import casim.ui.grid.CanvasGrid;
import javafx.scene.input.MouseEvent;

public class GridCellClickListener extends AbstractMouseEventHandler {

    public GridCellClickListener(final CanvasGrid grid) {
        super(grid);
    }

    @Override
    public void handle(MouseEvent event) {
        final var coord = this.getCoordinatesFromEvent(event);
        this.grid.getCell(coord)
            .ifPresent(cell -> this.grid.onCellClick(event.getButton(), cell, coord));
    }
    
}
