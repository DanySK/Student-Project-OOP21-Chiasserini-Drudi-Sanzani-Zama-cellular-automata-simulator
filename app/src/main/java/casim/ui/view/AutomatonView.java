package casim.ui.view;

import casim.controller.automaton.AutomatonController;
import casim.ui.components.grid.CanvasGridImpl;
import casim.ui.utils.AlertBuilderImpl;
import casim.ui.utils.StateColorMapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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

        AnchorPane.setLeftAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 0.0);
        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);

        this.widthProperty().addListener(this::onSizeChange);
    }

    private AutomatonController<T> getController() {
        return this.controller;
    }

    private void updateStats() {
        final var s = this.getController().getStats();
        this.statsLabel.setText(s.toString());
    }

    private void render() {
        if (this.controller.hasNext()) {
            final var state = this.controller.next();
            this.grid.setCells(state.map(this.colorMapper::toColor));  
            this.grid.draw();
        } else {
            final var builder = new AlertBuilderImpl();
            builder.buildDefaultInfo("No next step available.", this.owner)
                .show();
        }
    }

    private void init() {
        final var pane = new AnchorPane();
        pane.getChildren().add(this.grid);
        this.setCenter(pane);
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

    private void onSizeChange(ObservableValue<? extends Number> obs, final Number oldVal, final Number newVal) {
        this.grid.handleSizeChange(this.getWidth(), this.getHeight());
    }
}
