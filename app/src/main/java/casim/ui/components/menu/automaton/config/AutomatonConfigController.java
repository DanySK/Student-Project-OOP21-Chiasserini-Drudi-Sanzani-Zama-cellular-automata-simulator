package casim.ui.components.menu.automaton.config;

import org.apache.commons.lang3.math.NumberUtils;

import casim.model.Automata;
import casim.model.codi.CoDiConfig;
import casim.ui.components.page.PageContainer;
import casim.utils.Alerts;
import casim.utils.AppManager;
import casim.utils.ViewUtils;
import casim.utils.automaton.config.BaseConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller class for the fxml AutomatonConfigMenu.
 */
public class AutomatonConfigController {

    private static final String UNKNOWN_AUTOMATON = "Unknown automaton.";
    private static final String WRONG_MENU = "Wrong Configuration Menu.";
    private static final String NO_MODES_SET = "Please select a run mode";
    private static final String WRONG_SIZE = "Insert a valid integer number";
    private static final String SHOW_INFO = "The grid size is ";

    @FXML
    private VBox configView;

    @FXML
    private TextField sizeField;

    @FXML
    private ChoiceBox<String> modeSelector;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    private HBox extension;

    private final PageContainer container;
    private final Automata automata;

    /**
     * Construct a new {@link AutomatonConfigController}.
     * 
     * @param container the container of the menu.
     * @param controller the controller.
     */
    public AutomatonConfigController(final PageContainer container, final Automata automata) {
        this.container = container;
        this.automata = automata;
    }

    /**
     * Get the {@link PageContainer} where the menu is.
     * 
     * @return the {@link PageContainer} holding the menu.
     */
    public PageContainer getContainer() {
        return this.container;
    }

    /**
     * Initialize the controller.
     */
    @FXML
    protected void initialize() {
        ViewUtils.fitToAnchorPane(this.configView);
        final ObservableList<String> names = FXCollections.observableArrayList("Automatic", "Manual");
        this.modeSelector.setItems(names);
    }

    /**
     * Add a {@link Node} to the view.
     * Permits (also to the subclasses) to add a component to the view.
     * 
     * @param node the component to add
     */
    protected void addExtension(final Node node) {
        this.extension.getChildren().add(node);
    }

    @FXML
    private void onBackBtnClick(final ActionEvent event) {
        this.getContainer().popPage().getValue(); //check for error
    }

    @FXML
    private void onNextBtnClick(final ActionEvent event) {
        if (this.showAlertAndCheck(this.sizeField.getText())) {
            //TODO: Check if is automatic && check type of automata for config type
            final var config = this.getConfig();
            AppManager.showSimulation(this.automata, this.getContainer(), config);
        }
    }

    private BaseConfig getConfig() {
        final var isAutomatic = true;
        final int size = Integer.parseInt(this.sizeField.getText());

        switch (this.automata) {
            case CODI:
                return (BaseConfig) new CoDiConfig(size, size, size, isAutomatic);
            case RULE110:
            case WATOR:
                return new BaseConfig(size, size, isAutomatic);
            case BRYANS_BRAIN:
            case GAME_OF_LIFE:
            case LANGTONS_ANT:
                throw new UnsupportedOperationException(WRONG_MENU);
            default:
                throw new IllegalArgumentException(UNKNOWN_AUTOMATON);
        }
    }

    private boolean showAlertAndCheck(final String gridSize) {
        if (this.modeSelector.getSelectionModel().isEmpty()) {
            Alerts.ofShowAndWait(AlertType.ERROR, NO_MODES_SET);
            return false;
        }
        if (!NumberUtils.isCreatable(gridSize) || Integer.parseInt(gridSize) < 0) { //TODO: Better handling maybe Result.execute
            Alerts.ofShowAndWait(AlertType.ERROR, WRONG_SIZE);
            return false;
        } else {
            Alerts.ofShowAndWait(AlertType.INFORMATION, SHOW_INFO + gridSize);
            return true;
        }
    }

}
