package casim.ui.view.implementation;

import casim.controller.automaton.CoDiControllerImpl;
import casim.model.codi.cell.attributes.CoDiCellState;
import casim.ui.components.grid.CanvasGridImpl;
import casim.ui.components.page.PageContainer;
import casim.ui.utils.StateColorMapper;
import casim.utils.Alerts;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Implementation of CoDi's {@link AutomatonViewController}.
 */
public class CoDiViewController extends ConcurrentAutomatonViewController<CoDiCellState> {

    private static final String LAYER_INFO = "Remember you can change layer! (Default 0)"
            + "\nA -> go left (-1)"
            + "\nD -> go right (+1)";

    /**
     * Construct a new {@link CoDiViewController}.
     * 
     * @param container the {@link PageContainer} holding the view.
     * @param controller the {@link AutomatonController} controlling the view.
     * @param grid the {@link CanvasGridImpl} to be drawn.
     * @param colorMapper the {@link StateColorMapper} that translates cell states to colors.
     */
    public CoDiViewController(final PageContainer container, final CoDiControllerImpl controller,
            final CanvasGridImpl grid, final StateColorMapper<CoDiCellState> colorMapper) {
        super(container, controller, grid, colorMapper);
        Alerts.ofShowAndWait(AlertType.INFORMATION, LAYER_INFO);
    }

    @Override
    protected void viewConfig() {
        this.getView().addEventFilter(KeyEvent.KEY_PRESSED, this.changeLayerHandler());
    }

    private EventHandler<KeyEvent> changeLayerHandler() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode() == KeyCode.A) {  //TODO: is that right or synchronyzed is needed?
                    final var state = ((CoDiControllerImpl) CoDiViewController.this.getController()).outputLayerLeftShift();
                    CoDiViewController.this.setCellsDrawUpdateStats(state);
                } else if (event.getCode() == KeyCode.D) {
                    final var state = ((CoDiControllerImpl) CoDiViewController.this.getController()).outputLayerRightShift();
                    CoDiViewController.this.setCellsDrawUpdateStats(state);
               }
            }
        };
    }

}
