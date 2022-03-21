package casim.ui.components.menu.automaton;

import casim.model.Automata;
import casim.ui.components.menu.AbstractMenuButton;
import casim.ui.utils.AlertBuilderImpl;

public class AutomatonMenuButton extends AbstractMenuButton<Automata> {
    public AutomatonMenuButton(final String name, final Automata modelClass, final AutomatonMenu menu) {
        super(name, modelClass, menu);
    }

    @Override
    public void onClick() {
        //TODO: get the container from the menu and then push the automaton view (or the configuration) on top of the menu 
        final var builder = new AlertBuilderImpl();
        builder.buildDefaultInfo(this.getData().getName(), this.getMenu().getContainer().getOwner()).show();
    }
}