package casim.model.codi.cell;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import casim.model.abstraction.cell.AbstractCell;
import casim.model.codi.CellState;
import casim.model.codi.Direction;
import casim.model.codi.rule.RulesUtils;

/**
 * The CoDi cells.
 */
public class CoDiCell extends AbstractCell<CellState> {

    private int activationCounter;
    private final Optional<Direction> gate;
    private final Map<Direction, Boolean> chromosome;
    private final EnumMap<Direction, Integer> neighborsPreviousInput;

    /**
     * Construct a new  {@link CoDiCell} with specific initial values.
     * 
     * @param state the {@link CellState} of the cell.
     * @param activationCounter the value of the activation counter.
     * @param gate the gate of the cell (an empty optional if it's not defined).
     * @param neighborsPreviousInput the {@link EnumMap} containing the values of the prior inputs.
     */
    public CoDiCell(final CellState state, final int activationCounter, 
            final EnumMap<Direction, Integer> neighborsPreviousInput, final Optional<Direction> gate) {
        super(state);
        this.chromosome = new HashMap<>();
        for (final var dir: Direction.values()) {
            this.chromosome.put(dir, RulesUtils.rand50());
        }
        this.gate = gate;
        this.activationCounter = activationCounter;
        this.neighborsPreviousInput = neighborsPreviousInput;
    }

    /**
     * Return the gate of the {@link CoDiCell}, it's meaning depends from the {@link CellState} of the cell:
     *  Neuron -> direction where the axon point to;
     *  Axon -> direction where it receives the signal;
     *  Dendrite -> direction where it transmits the signal.
     *
     * @return an {@link Optional} which contain the {@link Direction} of the gate, it's empty if it's not already defined.
     */
    public Optional<Direction> getGate() {
        return gate;
    }

    /**
     * Return the opposite {@link Direction} to the one of the gate.
     * 
     * @return the gate's opposite {@link Direction}, an empty optional if the gate isn't defined.
     */
    public Optional<Direction> getOppositeToGate() {
        return this.gate.isPresent() ? Optional.of(this.gate.get().getOpposite()) : Optional.empty();
    }

    /**
     * The current value of the activation counter, the cell can transmit the signal only if a predetermined value is reached.
     * 
     * @return the current value of the activation counter.
     */
    public int getActivationCounter() {
        return activationCounter;
    }

    //TODO delete that
    /**
     * The value to set.
     * 
     * @param activationCounter the value to set.
     */
    public void setActivationCounter(final int activationCounter) {
        this.activationCounter = activationCounter;
    }

    /**
     * Return an {@link EnumMap} containing the previous output for each {@link Direction}.
     * 
     * @return Return an {@link EnumMap} containing the previous output for each {@link Direction}.
     */
    public EnumMap<Direction, Integer> getNeighborsPreviousInput() {
        return neighborsPreviousInput;
    }

    /**
     * Return a {@link Map} describing the chromosome of the cell.
     * The value is true in the directions where the cell can send the signal.
     * 
     * @return Return a {@link Map} representing the chromosome of the cell.
     */
    public Map<Direction, Boolean> getChromosome() {
        return Collections.unmodifiableMap(this.chromosome);
    }

}
