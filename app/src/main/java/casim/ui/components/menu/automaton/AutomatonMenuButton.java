package casim.ui.components.menu.automaton;

import casim.model.Automata;
import casim.ui.components.menu.AbstractMenuButton;
import casim.ui.utils.AlertBuilderImpl;
import casim.utils.AppManager;

/**
 * Bytton for the main menu.
 */
public class AutomatonMenuButton extends AbstractMenuButton<Automata> {
    /**
     * Build a new Menu button.
     *
     * @param name the string to display.
     * @param automata the automaton represents.
     * @param menu menu containing the button.
     */
    public AutomatonMenuButton(final String name, final Automata automata, final AutomatonMenu menu) {
        super(name, automata, menu);
    }

    @Override
    public void onClick() {
        final var res = AppManager.showConfigMenu(this.getData(), this.getMenu().getContainer());
        if (res.isError()) {
            new AlertBuilderImpl().buildDefaultError(
                "Error opening the config page.", this.getMenu().getContainer().getOwner());
        }
    }
}
