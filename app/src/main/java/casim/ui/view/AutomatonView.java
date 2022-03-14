package casim.ui.view;

import casim.controller.automaton.AutomatonController;
import casim.ui.components.grid.CanvasGridImpl;
import casim.ui.utils.StateColorMapper;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

public class AutomatonView<T> extends BorderPane {
    private static final String STATS_LABEL = "Stats";
    private static final String NEXT_BTN_LABEL = "Next";

    private final Window owner;
    private final AutomatonController<T> controller;
    private final CanvasGridImpl grid;
    private final Label statsLabel = new Label(STATS_LABEL);
    private final Button nextStepBtn = new Button(NEXT_BTN_LABEL);
    private final StateColorMapper<T> colorMapper;

    public AutomatonView(
        final Window owner, 
        final AutomatonController<T> controller, 
        final CanvasGridImpl grid, 
        final StateColorMapper<T> colorMapper) {

        this.owner = owner;
        this.controller = controller;
        this.grid = grid;
        this.colorMapper = colorMapper;
        this.init();
    }

    private AutomatonController<T> getController() {
        return this.controller;
    }

    private void updateStats() {
        final var s = this.getController().getStats();
        this.statsLabel.setText(s.toString());
    }

    private void render() {
        //TODO: Check for hasNext
        final var state = this.controller.next();
        this.grid.setCells(state.map(this.colorMapper::toColor));  
        this.grid.draw();
    }

    private void init() {
        this.setCenter(this.grid);
        this.setTop(this.statsLabel);
        this.setBottom(this.nextStepBtn);
        this.nextStepBtn.setOnAction(e -> {
            this.render();
            this.updateStats();
        });
        this.grid.setCells(this.controller.getGrid().map(this.colorMapper::toColor));
        this.grid.draw();
        this.updateStats();
    }
}
