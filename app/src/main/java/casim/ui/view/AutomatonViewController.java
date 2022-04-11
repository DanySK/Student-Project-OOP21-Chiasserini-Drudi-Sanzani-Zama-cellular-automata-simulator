package casim.ui.view;

import casim.controller.automaton.AutomatonController;
import casim.ui.components.grid.CanvasGridImpl;
import casim.ui.components.page.PageContainer;
import casim.ui.utils.AlertBuilderImpl;
import casim.ui.utils.StateColorMapper;
import casim.utils.ViewUtils;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class AutomatonViewController<T> {
    private final static String NO_NEXT_STEP = "No next step available.";

    @FXML
    private VBox automatonView;

    @FXML
    private Label statsLbl;

    @FXML
    private Button nextBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Pane automatonPane;

    private final CanvasGridImpl grid;
    private final PageContainer container;
    private final StateColorMapper<T> colorMapper;
    private final AutomatonController<T> controller;

    public AutomatonViewController(final PageContainer container, final AutomatonController<T> controller,
            final CanvasGridImpl grid, final StateColorMapper<T> colorMapper) {
        this.container = container;
        this.controller = controller;
        this.grid = grid;
        this.colorMapper = colorMapper;
        this.grid.setCells(this.controller.getGrid().map(this.colorMapper::toColor));
    }

    @FXML
    protected void onExitBtnClick(final ActionEvent event) {
        this.getContainer().popPage();
    }

    @FXML
    protected void onNextBtnClick(final ActionEvent event) {
        this.render();
        this.updateStats();        
    }

    @FXML
    protected void initialize() {
        ViewUtils.fitToAnchorPane(this.automatonView);
        this.automatonPane.getChildren().add(this.grid);
        this.automatonPane.widthProperty().addListener(this::onSizeChange);
        this.automatonPane.heightProperty().addListener(this::onSizeChange);
    }

    protected void render() {
        if (this.controller.hasNext()) {
            this.updateGrid();
        } else {
            new AlertBuilderImpl()
            .buildDefaultInfo(NO_NEXT_STEP, this.container.getOwner())
            .show();
        }
    }
    
    protected void updateStats() {
        final var s = this.controller.getStats();
        Platform.runLater(() -> this.statsLbl.setText(s.toString()));
    }

    protected PageContainer getContainer() {
        return this.container;
    }

    protected AutomatonController<T> getController() {
        return this.controller;
    }

    protected void onSizeChange(final ObservableValue<? extends Number> obs, final Number oldVal, final Number newVal) {
        this.grid.handleSizeChange(this.automatonPane.getWidth(), this.automatonPane.getHeight());
    }

    private void updateGrid() {
        final var state = this.controller.next();
        this.grid.setCells(state.map(this.colorMapper::toColor));
        Platform.runLater(() -> this.grid.draw());
    }
}
