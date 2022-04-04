package casim.utils.automaton;

import casim.model.bryansbrain.BryansBrain;

public interface AutomatonFactory {
    BryansBrain getBryansBrainRandom(int cols, int rows);
}
