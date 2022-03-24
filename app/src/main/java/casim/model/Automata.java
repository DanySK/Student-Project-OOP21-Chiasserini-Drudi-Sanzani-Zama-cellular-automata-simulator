package casim.model;

/**
 * Automata enum.
 * 
 */
public enum Automata {
    BRYANSBRAIN("Bryan's Brain");
    
    private final String name;
    
    /**
     * Costructor of the class.
     * 
     * @param name of the automaton
     */
    private Automata(final String name) {
        this.name = name;
    }

    /**
     * Get the name of the automaton.
     * 
     * @return the name of the automaton.
     */
    public String getName()
    {
        return this.name;
    }
}