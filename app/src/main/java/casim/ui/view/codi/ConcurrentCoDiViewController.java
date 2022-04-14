package casim.ui.view.codi;

import casim.controller.automaton.CoDiControllerImpl;
import casim.model.codi.cell.attributes.CoDiCellState;
import casim.ui.components.grid.CanvasGridImpl;
import casim.ui.components.page.PageContainer;
import casim.ui.utils.StateColorMapper;
import casim.ui.view.ConcurrentAutomatonViewController;
import casim.utils.grid.Grid2D;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Implementation of CoDi's {@link AutomatonViewController}.
 */
public class ConcurrentCoDiViewController extends ConcurrentAutomatonViewController<CoDiCellState> {

    /**
     * Construct a new {@link ConcurrentCoDiViewController}.
     * 
     * @param container the {@link PageContainer} holding the view.
     * @param controller the {@link AutomatonController} controlling the view.
     * @param grid the {@link CanvasGridImpl} to be drawn.
     * @param colorMapper the {@link StateColorMapper} that translates cell states to colors.
     */
    public ConcurrentCoDiViewController(final PageContainer container, final CoDiControllerImpl controller,
            final CanvasGridImpl grid, final StateColorMapper<CoDiCellState> colorMapper) {
        super(container, controller, grid, colorMapper);
        CoDiViewUtils.showStartAlert(container);
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.getView().addEventFilter(KeyEvent.KEY_PRESSED, this.changeLayerHandler());
    }

    @Override
    protected synchronized void updateView(final Grid2D<CoDiCellState> state) {
        synchronized (this) {
            super.updateView(state);
        }
    }

    private EventHandler<KeyEvent> changeLayerHandler() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode() == KeyCode.A) {
                    final var state = ((CoDiControllerImpl) ConcurrentCoDiViewController.this.getController())
                            .outputLayerLeftShift();
                    ConcurrentCoDiViewController.this.updateView(state);
                } else if (event.getCode() == KeyCode.D) {
                    final var state = ((CoDiControllerImpl) ConcurrentCoDiViewController.this.getController())
                            .outputLayerRightShift();
                    ConcurrentCoDiViewController.this.updateView(state);
               }
            }
        };
    }

}
