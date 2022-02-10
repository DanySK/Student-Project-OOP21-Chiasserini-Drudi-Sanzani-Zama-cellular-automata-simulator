package casim.model;

import casim.utils.grid.Grid;

/**
 *  An interface which describes an {@link Automaton}.
 *
 *  @param <T> the type of the finite states of the {@link Automaton}'s {@link Cell}.
 */
public interface Automaton<T> {

    /**
     * Method called to let {@link Automaton} to do the next step.
     */
    void doStep();

    /**
     * Get a {@link Grid} describing the current {@link Automaton}'s state.
     * 
     * @return {@link Grid} A {@link Grid} of {@link CellAttributes}
     */
    Grid<CellAttributes<T>> getAttributesGrid();

}
