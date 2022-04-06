package casim.utils.automaton;

import casim.model.bryansbrain.BryansBrain;
import casim.model.bryansbrain.BryansBrainConfig;
import casim.model.codi.CoDi;
import casim.model.langtonsant.LangtonsAnt;
import casim.model.wator.Wator;

/**
 * A factory for the automaton creation.
 */
public interface AutomatonFactory {

    /**
     * Return a new {@link BryansBrain} automaton.
     * 
     * @param config the {@link ByansBrainConfig} containing
     *          the automaton's configuration values.
     * @return a new {@link BryansBrain} automaton.
     */
    BryansBrain getBryansBrainRandom(BryansBrainConfig config);

    /**
     * Return a new {@link CoDi} automaton.
     * 
     * @param cols the cols of the automaton grid.
     * @param rows the rows of the automaton grid.
     * @param depth the depth of the automaton's grid.
     * @return a new {@link CoDi} automaton.
     */
    CoDi getCoDi(int cols, int rows, int depth);

    /**
     * Returns a new {@link LangtonsAnt} automaton.
     * 
     * @param cols the columns of the automaton grid.
     * @param rows the rows of the automaton grid.
     * @param antNumber the number of ants that will
     *          randomly populate the grid.
     * @param wrapping a boolean indicating if the
     *          grid will be wrapping or not.
     *          If true the ants won't die when reaching
     *          the edge of the grid but will warp to the
     *          opposite edge.
     * @return a new {@link LangtonsAnt} automaton.
     */
    LangtonsAnt getLangtonsAnt(int cols, int rows, int antNumber, boolean wrapping);

    /**
     * Returns a new {@link Wator} automaton.
     * 
     * @param cols the colums of the automaton grid.
     * @param rows the rows of the automaton grid.
     * @return a new {@link Wator} autmaton.
     */
    Wator getWator(int cols, int rows);
}
