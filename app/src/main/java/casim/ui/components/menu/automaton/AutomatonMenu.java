package casim.ui.components.menu.automaton;

import java.util.stream.Collectors;

import casim.controller.menu.MenuController;
import casim.ui.components.menu.AbstractMenu;
import casim.ui.components.page.PageContainer;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;

public class AutomatonMenu extends AbstractMenu {
    public AutomatonMenu(final PageContainer container, final MenuController controller) {
        super(container, controller);
        this.init();
    }
    
    private void init() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15.0);
        AnchorPane.setLeftAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 0.0);
        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);

        this.addControls(this.getMenuController().getMenuItems().stream()
            .map(x -> new AutomatonMenuButton(x.getName(), x, this))
            .collect(Collectors.toList())
        );
    }
}
