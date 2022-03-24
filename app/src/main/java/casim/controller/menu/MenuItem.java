package casim.controller.menu;

/**
 * Object containing the name, the type and the states type of an automaton.
 *
 */
public class MenuItem {

    private final String name;
    private final Class<?> automatonClass;
    private final Class<?> stateClass;

    /**
     * Constructor of the class.
     * 
     * @param name of the automaton.
     * @param automatonClass the model class of the automaton.
     * @param stateClass the state class of the automaton.
     */
    public MenuItem(final String name, final Class<?> automatonClass, final Class<?> stateClass) {
        this.name = name;
        this.automatonClass = automatonClass;
        this.stateClass = stateClass;
    }

    /**
     * Get the name of the automaton.
     *
     * @return the name of the automaton.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the model class of the automaton.
     * 
     * @return the model class of the automaton.
     */
    public Class<?> getAutomatonClass() {
        return this.automatonClass;
    }

    /**
     * Get the state class of the automaton.
     * 
     * @return the state class of the automaton.
     */
    public Class<?> getStateClass() {
        return this.stateClass;
    }

}
