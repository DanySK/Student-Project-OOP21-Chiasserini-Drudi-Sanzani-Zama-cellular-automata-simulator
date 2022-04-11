package casim.ui.view;

import casim.controller.automaton.AutomatonController;
import casim.ui.components.grid.CanvasGridImpl;
import casim.ui.components.page.PageContainer;
import casim.ui.utils.AlertBuilderImpl;
import casim.ui.utils.StateColorMapper;
import casim.utils.ViewUtils;
import casim.utils.grid.Grid2D;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
 
public class AutomatonViewController<T, S extends AutomatonController<T>> {
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

    private CanvasGridImpl grid;
    private PageContainer container;
    private StateColorMapper<T> colorMapper;
    private S controller;

    public AutomatonViewController() {

    }

    public void initData(
        final PageContainer container, 
        final S controller, 
        final CanvasGridImpl grid, 
        final StateColorMapper<T> colorMapper) {

        this.container = container;
        this.controller = controller;
        this.grid = grid;
        this.colorMapper = colorMapper;

        this.grid.setCells(this.controller.getGrid().map(this.colorMapper::toColor));

        this.automatonPane.getChildren().add(this.grid);
        this.automatonPane.widthProperty().addListener(this::onSizeChange);
        this.automatonPane.heightProperty().addListener(this::onSizeChange);
    }

    /**
     * Return the {@link Node} which contains the view.
     * 
     * @return the {@link Node} which contains the view.
     */
    protected Node getView() {
        return this.automatonView;
    }

    /**
     * Return the controller linked to the view.
     * 
     * @return the controller linked to the view.
     */
    protected S getController() {
        return this.controller;
    }

    @FXML
    private void initialize() {
        ViewUtils.fitToAnchorPane(this.automatonView);
    }

    @FXML
    private void onExitBtnClick(ActionEvent event) {
        this.container.popPage();
    }

    @FXML
    private void onNextBtnClick(ActionEvent event) {
        this.render();
        this.updateStats();
    }

    private void render() {
        if (this.controller.hasNext()) {
            final var state = this.controller.next();
            this.setCellsAndDraw(state);
        } else {
            final var builder = new AlertBuilderImpl();
            builder.buildDefaultInfo(NO_NEXT_STEP, this.container.getOwner())
                .show();
        }
    }

    private void setCellsAndDraw(final Grid2D<T> state) {
        this.grid.setCells(state.map(this.colorMapper::toColor));
        this.grid.draw();
    }

    private void updateStats() {
        final var s = this.controller.getStats();
        this.statsLbl.setText(s.toString());
    }

    /**
     * Set the {@link CanvasGridImpl}, draw it and update the stats.
     * 
     * @param state the grid of state to map to a {@link CanvasGridImpl}.
     */
    protected void setCellsDrawUpdateStats(final Grid2D<T> state) {
        this.setCellsAndDraw(state);
        this.updateStats();
    }

    private void onSizeChange(ObservableValue<? extends Number> obs, final Number oldVal, final Number newVal) {
        this.grid.handleSizeChange(this.automatonPane.getWidth(), this.automatonPane.getHeight());
    }
}