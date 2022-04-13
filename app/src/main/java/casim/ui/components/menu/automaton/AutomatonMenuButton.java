package casim.ui.components.menu.automaton;

import casim.model.Automata;
import casim.ui.components.menu.AbstractMenuButton;
import casim.utils.AppManager;

/**
 * Bytton for the main menu.
 */
public class AutomatonMenuButton extends AbstractMenuButton<Automata> {
    /**
     * Build a new Menu button.
     *
     * @param name the string to display.
     * @param automata the automaton representes.
     * @param menu menu containing the buttion.
     */
    public AutomatonMenuButton(final String name, final Automata automata, final AutomatonMenu menu) {
        super(name, automata, menu);
    }

    @Override
    public void onClick() {
        AppManager.showConfigMenu(this.getData(), this.getMenu().getContainer());
    }
}
