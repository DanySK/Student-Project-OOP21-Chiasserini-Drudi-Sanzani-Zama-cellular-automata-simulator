package casim.ui.components.menu.automaton;

import java.util.stream.Collectors;

import casim.controller.menu.MenuController;
import casim.ui.components.menu.AbstractMenu;
import casim.ui.components.page.PageContainer;

public class AutomatonMenu extends AbstractMenu {
    public AutomatonMenu(final PageContainer container, final MenuController controller) {
        super(container, controller);

        this.addControls(
            controller.getMenuItems().stream()
                .map(x -> new AutomatonMenuButton(x.getName(), x, this))
                .collect(Collectors.toList())
        );
    }
}
