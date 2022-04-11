package casim.ui.components.menu.automaton.config;

import casim.controller.menu.MenuController;
import casim.ui.components.page.PageContainer;
import javafx.scene.control.CheckBox;

/**
 * Controller class for the fxml AutomatonConfigMenu with also the wrap configuration.
 */
public class AutomatonWrapConfigController extends AutomatonConfigController {
    private final CheckBox checkBox;

    /**
     * Construct a new {@link AutomatonWrapConfigController}.
     * 
     * @param container the container of the menu.
     * @param controller the controller.
     */
    public AutomatonWrapConfigController(final PageContainer container, final MenuController controller) {
        super(container, controller);
        this.checkBox = new CheckBox("Wrapped Grid");
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.addExtension(checkBox);
    }

    @Override
    protected void valuesToController() {

    }

}
