package casim.ui.components.menu.automaton;

import casim.model.Automata;
import casim.ui.components.menu.AbstractMenuButton;
import casim.ui.utils.AlertBuilderImpl;

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
        //TODO: get the container from the menu and then push the automaton view (or the configuration) on top of the menu 
        final var builder = new AlertBuilderImpl();
        builder.buildDefaultInfo(this.getData().getName(), this.getMenu().getContainer().getOwner()).show();
    }
}
