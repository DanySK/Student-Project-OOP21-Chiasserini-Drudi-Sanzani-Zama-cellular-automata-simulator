package casim.ui.view.codi;

import casim.controller.automaton.AutomatonController;
import casim.controller.automaton.CoDiControllerImpl;
import casim.model.codi.cell.attributes.CoDiCellState;
import casim.ui.components.grid.CanvasGridImpl;
import casim.ui.components.page.PageContainer;
import casim.ui.utils.StateColorMapper;
import casim.ui.view.AutomatonViewController;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Implementation of CoDi's {@link AutomatonViewController}.
 */
public class CoDiViewController extends AutomatonViewController<CoDiCellState> {

    /**
     * Construct a new {@link CodiViewController}.
     * 
     * @param container the {@link PageContainer} holding the view.
     * @param controller the {@link AutomatonController} controlling the view.
     * @param grid the {@link CanvasGridImpl} to be drawn.
     * @param colorMapper the {@link StateColorMapper} that translates cell states to colors.
     */
    public CoDiViewController(final PageContainer container, final AutomatonController<CoDiCellState> controller,
            final CanvasGridImpl grid, final StateColorMapper<CoDiCellState> colorMapper) {
        super(container, controller, grid, colorMapper);
        CoDiViewUtils.showStartAlert(container);
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.getView().addEventFilter(KeyEvent.KEY_PRESSED, this.changeLayerHandler());
    }

    private EventHandler<KeyEvent> changeLayerHandler() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode().equals(KeyCode.A)) {
                    final var state = 
                            ((CoDiControllerImpl) CoDiViewController.this.getController()).outputLayerLeftShift();
                    CoDiViewController.this.updateView(state); 
                } else if (event.getCode().equals(KeyCode.D)) {
                    final var state = 
                            ((CoDiControllerImpl) CoDiViewController.this.getController()).outputLayerRightShift();
                    CoDiViewController.this.updateView(state);
               }
            }
        };
    }
}
