package casim.ui.components.menu.automaton.config;

import org.apache.commons.lang3.math.NumberUtils;

import casim.model.Automata;
import casim.model.codi.CoDiConfig;
import casim.ui.components.page.PageContainer;
import casim.ui.utils.AlertBuilderImpl;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller class for the fxml AutomatonConfigMenu.
 */
public class AutomatonConfigController {

    /**
     * Wrong menu error message.
     */
    protected static final String WRONG_MENU = "Wrong Configuration Menu.";
    private static final String NO_MODES_SET = "Please select a run mode";
    private static final String WRONG_SIZE = "Insert a valid integer number";
    private static final String SHOW_INFO = "The grid size is ";
    private static final String AUTOMATIC = "Automatic";
    private static final String MANUAL = "Manual";

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
     * @param automata the automata.
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
        final ObservableList<String> names = FXCollections.observableArrayList(AUTOMATIC, MANUAL);
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

    /**
     * Return the automata.
     * 
     * @return the automata.
     */
    protected Automata getAutomata() {
        return this.automata;
    }

    /**
     * Return the size on the input text field.
     * 
     * @return the size as string.
     */
    protected String getSizeText() {
        return this.sizeField.getText();
    }

    /**
     * Returns whether the automatic mode is set or not.
     * 
     * @return true if the automatic mode is set, false otherwise.
     */
    protected boolean isAutomatic() {
        return AUTOMATIC.equals(this.modeSelector.getValue());
    }

    /**
     * Returns the config object of the automaton.
     * 
     * @return the config object.
     */
    protected BaseConfig getConfig() {
        final var isAutomatic = this.isAutomatic();
        final int size = Integer.parseInt(this.getSizeText());

        switch (this.getAutomata()) {
            case CODI:
                return (BaseConfig) new CoDiConfig(size, size, size, isAutomatic);
            case RULE110:
            case WATOR:
                return new BaseConfig(size, size, isAutomatic);
            default:
                throw new UnsupportedOperationException(WRONG_MENU);
        }
    }

    @FXML
    private void onBackBtnClick(final ActionEvent event) {
        this.getContainer().popPage().getValue(); //check for error //TODO: to check if exist a previous page maybe it's a good thing add a method to page container 
    }

    @FXML
    private void onNextBtnClick(final ActionEvent event) {
        if (this.showAlertAndCheck(this.sizeField.getText())) {
            //TODO: Check if is automatic && check type of automata for config type
            final var config = this.getConfig();
            AppManager.showSimulation(this.getAutomata(), this.getContainer(), config);
        }
    }

    private boolean showAlertAndCheck(final String gridSize) {
        final var alertBuilder = new AlertBuilderImpl();
        if (this.modeSelector.getSelectionModel().isEmpty()) {
            alertBuilder.buildDefaultError(NO_MODES_SET, this.getContainer().getOwner())
            .showAndWait();
            return false;
        } else if (!NumberUtils.isCreatable(gridSize) || Integer.parseInt(gridSize) < 0) { //TODO: Better handling maybe Result.execute
            alertBuilder.buildDefaultError(WRONG_SIZE, this.getContainer().getOwner())
            .showAndWait();
            return false;
        } else {
            alertBuilder.buildDefaultInfo(SHOW_INFO + gridSize, this.getContainer().getOwner())
            .showAndWait();
            return true;
        }
    }

}
