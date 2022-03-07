package casim.ui.components.menu.automaton;

import casim.ui.components.menu.AbstractMenuButton;

public class AutomatonMenuButton<T> extends AbstractMenuButton<Class<T>> {
    public AutomatonMenuButton(final String name, Class<T> modelClass, final AutomatonMenu menu) {
        super(name, modelClass, menu);
    }

    @Override
    public void onClick() {
        //TODO: get the container from the menu and then push the automaton view (or the configuration) on top of the menu   
    }
}
