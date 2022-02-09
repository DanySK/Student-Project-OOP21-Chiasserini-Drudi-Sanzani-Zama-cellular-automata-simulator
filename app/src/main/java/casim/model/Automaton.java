package casim.model;

import casim.utils.grid.Grid;

/**
 *  An interface which describes an {@link Automaton}.
 */
public interface Automaton {

    /**
     * Method called to let the {@link Automaton} to do the next step.
     */
    void doStep();

    /**
     * Get the {@link Grid} represents the current {@link Automaton}'s state.
     * 
     * @param <T> the type of each cell of the {@link Grid} returned.
     * @return {@link Grid} of type T, {@link Grid} describing the current {@link Automaton}'s state.
     */
    <T> Grid<T> getGrid();

}
