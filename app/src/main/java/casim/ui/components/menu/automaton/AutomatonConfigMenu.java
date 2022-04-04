package casim.ui.components.menu.automaton;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import casim.controller.menu.MenuController;
import casim.ui.components.menu.AbstractMenu;
import casim.ui.components.page.PageContainer;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * An abstract class for the {@link casim.model.abstraction.automaton.Automaton}.
 */
public abstract class AutomatonConfigMenu extends AbstractMenu {

    private static final double SPACING = 15.0;

    /**
     *Construct a new {@link AutomatonConfigMenu}.
     * 
     * @param container the container of the menu.
     * @param controller the controller.
     */
    public AutomatonConfigMenu(final PageContainer container, final MenuController controller) {
        super(container, controller);
        this.init();
    }

    private void init() {
        this.setSpacing(SPACING);
        this.setAlignment(Pos.CENTER);
        AnchorPane.setLeftAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 0.0);
        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);
        this.buildMenu();
    }

    private void buildMenu() {
        final HBox hbox1 = new HBox(SPACING);
        final HBox hbox2 = new HBox(SPACING);
        hbox1.setAlignment(Pos.CENTER);
        hbox2.setAlignment(Pos.CENTER);
        final TextField text = new TextField();
        final Button nextButton = new Button("Next");
        final Button backButton = new Button("Back");
        final Label introLabel = new Label("AUTOMATON CONFIGURATION");
        final Label dimensionLabel = new Label("Grid Dimension");
        nextButton.setOnAction(e -> {
            this.showDimensionAlert(text.getText());
            //TODO: passa al controller il valore e crea l'automa
        });
        if (this.getContainer().popPage().isError()) {
            backButton.setDisable(true);
        } else {
            backButton.setOnAction(e -> this.getContainer().popPage().getValue());
        }

        hbox1.getChildren().add(dimensionLabel);
        hbox1.getChildren().add(text);
        hbox2.getChildren().add(nextButton);
        hbox2.getChildren().add(backButton);
        this.addNodes(List.of(introLabel, hbox1, hbox2));
    }

    private void showDimensionAlert(final String gridSize) {
        final Alert alert = new Alert(AlertType.INFORMATION);
        if (!NumberUtils.isCreatable(gridSize) || Integer.parseInt(gridSize) < 0) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Insert a valid integer number");
        } else {
            alert.setContentText("The grid size is " + gridSize);
        }
        alert.show();
    }

}
