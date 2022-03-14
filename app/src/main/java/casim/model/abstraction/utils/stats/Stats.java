package casim.model.abstraction.utils.stats;

import java.util.Map;

/**
 * An interface used to describe stats about an {@link casim.model.abstraction.automaton.Automaton}.
 * 
 *  @param <T> the type of the finite states of the {@link casim.model.abstraction.automaton.Automaton}'s {@link casim.model.abstraction.cell.Cell}.
 */
public interface Stats<T>   {

    /**
     * Get the number of iterations done by the Automaton.
     * 
     * @return the number of iterations.
     */
    int getIteration();

    /**
     * Get the number of cells alive for each types.
     * 
     * @return a map:
     *   - Key: the states that a {@link casim.model.abstraction.cell.Cell} can assume;
     *   - Value: the number of cells of that type.
     */
    Map<T, Integer> getCellStats();

    /**
     * Return the string representation of the stats.
     * 
     * @return the string representation.
     */
    String toString();

}
