package casim.ui.components.menu.automaton;

import java.util.stream.Collectors;

import casim.controller.menu.MenuController;
import casim.ui.components.menu.AbstractMenu;
import casim.ui.components.page.PageContainer;
import casim.utils.ViewUtils;
import javafx.geometry.Pos;

/**
 * Main menu of the application.
 */
public class AutomatonMenu extends AbstractMenu {
    private static final double BUTTON_SPACING = 15.0;

    /**
     * Construct a new menu.
     * 
     * @param container the {@link PageContainer} holding the menu.
     * @param controller the {@link MenuController} controlling the menu.
     */
    public AutomatonMenu(final PageContainer container, final MenuController controller) {
        super(container, controller);
        this.init();
    }

    private void init() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(BUTTON_SPACING);
        ViewUtils.fitToAnchorPane(this);

        this.addNodes(this.getMenuController().getMenuItems().stream()
            .map(x -> new AutomatonMenuButton(x.getName(), x, this))
            .collect(Collectors.toList())
        );
    }
}
