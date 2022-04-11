package casim.model;

/**
 * Automata enum.
 * 
 */
public enum Automata {
    BRYANS_BRAIN("Bryan's Brain"),
    RULE110("Rule 110"),
    WATOR("Predators and preys (Wator)"),
    LANGTONS_ANT("Langton's ant"),
    CODI("CoDi"),
    GAME_OF_LIFE("Conway's game of life");
    
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