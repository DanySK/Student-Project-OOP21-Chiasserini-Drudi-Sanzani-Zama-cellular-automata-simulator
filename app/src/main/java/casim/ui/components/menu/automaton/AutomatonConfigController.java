package casim.ui.components.menu.automaton;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import casim.controller.menu.MenuController;
import casim.ui.components.page.PageContainer;
import casim.utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

/**
 * Controller class for the fxml AutomatonConfigMenu.
 */
public class AutomatonConfigController {

    private static final String NO_MODES_SET = "Please select a run mode";
    private static final String WRONG_SIZE = "Insert a valid integer number";
    private static final String SHOW_SIZE = "The grid size is ";

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

    private final PageContainer container;
    private final MenuController controller;

    /**
     * Construct a new {@link AutomatonConfigController}.
     * 
     * @param container the container of the menu.
     * @param controller the controller.
     */
    public AutomatonConfigController(final PageContainer container, final MenuController controller) {
        this.container = container;
        this.controller = controller;
    }

    /**
     * Get the {@link MenuController}.
     * 
     * @return the {@link MenuController} .
     */
    public MenuController getMenuController() {
        return this.controller;
    }

    /**
     * Get the {@link PageContainer} where the menu is.
     * 
     * @return the {@link PageContainer} holding the menu.
     */
    public PageContainer getContainer() {
        return this.container;
    }

    @FXML
    private void initialize() {
        ViewUtils.fitToAnchorPane(this.configView);
        this.backButton.setDisable(this.checkContainerPreviousPageNotExist());
        final ObservableList<String> names = FXCollections.observableArrayList("Automatic", "Manual");
        this.modeSelector.setItems(names);
    }

    private boolean checkContainerPreviousPageNotExist() {
        return this.getContainer().popPage().isError();
    }

    @FXML
    private void onBackBtnClick(final ActionEvent event) {
        backButton.setOnAction(e -> this.getContainer().popPage().getValue());
    }

    @FXML
    private void onNextBtnClick(final ActionEvent event) {
        if (this.showAlertAndCheck(this.sizeField.getText())) {
            //TODO: send the size and the mode to the controller (DTO)?
        }
    }

    private boolean showAlertAndCheck(final String gridSize) {
        if (this.modeSelector.getSelectionModel().isEmpty()) {
            this.showAlert(AlertType.ERROR, NO_MODES_SET);
            return false;
        }
        if (!NumberUtils.isCreatable(gridSize) || Integer.parseInt(gridSize) < 0) {
            this.showAlert(AlertType.ERROR, WRONG_SIZE);
            return false;
        } else {
            this.showAlert(AlertType.INFORMATION, SHOW_SIZE + gridSize);
            return true;
        }
    }

    private void showAlert(final AlertType type, final String content) {
        final Alert alert = new Alert(type);
        alert.setAlertType(type);
        alert.setContentText(content);
        alert.show();
    }

}
