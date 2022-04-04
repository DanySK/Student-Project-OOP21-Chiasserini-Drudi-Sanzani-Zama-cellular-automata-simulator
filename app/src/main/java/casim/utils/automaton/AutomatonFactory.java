package casim.utils.automaton;

import casim.model.bryansbrain.BryansBrain;
import casim.model.codi.CoDi;

/**
 * A factory for the automaton creation.
 */
public interface AutomatonFactory {

    /**
     * Return a new {@link BryansBrain} automaton.
     * 
     * @param cols the cols of the automaton grid.
     * @param rows the rows of the automaton grid.
     * @return a new {@link BryansBrain} automaton.
     */
    BryansBrain getBryansBrainRandom(int cols, int rows);

    /**
     * Return a new {@link CoDi} automaton.
     * 
     * @param cols the cols of the automaton grid.
     * @param rows the rows of the automaton grid.
     * @param depth the depth of the automaton's grid.
     * @return a new {@link CoDi} automaton.
     */
    CoDi getCoDi(int cols, int rows, int depth);
}
