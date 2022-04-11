package casim.ui.view;

import casim.controller.automaton.CoDiControllerImpl;
import casim.model.codi.cell.attributes.CoDiCellState;
import casim.ui.components.grid.CanvasGridImpl;
import casim.ui.components.page.PageContainer;
import casim.ui.utils.StateColorMapper;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;

/**
 * Implementation of CoDi's {@link AutomatonViewController}.
 */
public class CoDiViewController extends AutomatonViewController<CoDiCellState, CoDiControllerImpl> {

    private static final String LAYER_INFO = "Remember you can change layer! (Default 0)\nA -> go left (-1)\nD -> go right (+1)";

    @Override
    public void initData(
            final PageContainer container, 
            final CoDiControllerImpl controller, 
            final CanvasGridImpl grid, 
            final StateColorMapper<CoDiCellState> colorMapper) {
            this.showAlert();
            super.initData(container, controller, grid, colorMapper);
            this.getView().addEventFilter(KeyEvent.KEY_PRESSED, this.changeLayerHandler());
        }

    private EventHandler<KeyEvent> changeLayerHandler() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode() == KeyCode.A) {
                    final var state = CoDiViewController.this.getController().outputLayerLeftShift();
                    CoDiViewController.this.setCellsDrawUpdateStats(state);
                } else if (event.getCode() == KeyCode.D) {
                    final var state = CoDiViewController.this.getController().outputLayerRightShift();
                    CoDiViewController.this.setCellsDrawUpdateStats(state);
               }
            }
        };
    }

    private void showAlert() {
        final Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(LAYER_INFO);
        alert.initModality(Modality.WINDOW_MODAL);
        alert.showAndWait();
    }
}
