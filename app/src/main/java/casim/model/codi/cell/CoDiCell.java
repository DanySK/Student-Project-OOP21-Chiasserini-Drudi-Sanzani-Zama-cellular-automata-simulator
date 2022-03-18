package casim.model.codi.cell;

import java.util.EnumMap;
import java.util.Optional;

import casim.model.abstraction.cell.AbstractCell;
import casim.model.codi.CellState;
import casim.model.codi.Direction;

/**
 * The CoDi cells.
 */
public class CoDiCell extends AbstractCell<CellState> {

    private int activationCounter;
    private Optional<Direction> gate;
    private EnumMap<Direction, Integer> neighborsPreviousInput;

    /**
     * Constructor of a default {@link CoDiCell}.
     * 
     * @param state the {@link CellState} of the cell.
     */
    public CoDiCell(final CellState state) {
        this(state, 0, new EnumMap<>(Direction.class));
    }

    /**
     * Constructor a {@link CoDiCell} with specific initial values.
     * 
     * @param state the {@link CellState} of the cell.
     * @param activationCounter the value of the activation counter.
     * @param neighborsPriorInput the {@link EnumMap} containing the values of the prior inputs.
     */
    public CoDiCell(final CellState state, final int activationCounter, 
            final EnumMap<Direction, Integer> neighborsPriorInput) {
        super(state);
        this.activationCounter = activationCounter;
        this.neighborsPreviousInput = neighborsPriorInput;
    }

    /**
     * Return the gate of the {@link CoDiCell}, it's meaning depends from the type of the cell {@link CellState}:
     *  Neuron -> direction where the axon point;
     *  Axon -> direction where it receives the signal;
     *  Dentrite -> direction where it transmits the signal.
     *
     * @return an {@link Optional} which contain the {@link Direction} of the gate, it's empty if it's not already defined.
     */
    public Optional<Direction> getGate() {
        return gate;
    }

    /**
     * Return the opposite {@link Direction} to the one of the gate.
     * 
     * @return the gate's opposite {@link Direction}.
     */
    public Optional<Direction> getOppositeToGate() {
        return this.gate.isPresent() ? Optional.of(this.gate.get().getOpposite()) : Optional.empty();
    }

    /**
     * Set the gate of the {@link CoDiCell}.
     * 
     * @param gate the gate to set.
     */
    public void setGate(final Direction gate) {
        this.gate = Optional.of(gate);
    }

    /**
     * The current value of the activation counter, the cell can transmit the signal only if a predetermined value is reached.
     * 
     * @return the current value of the activation counter.
     */
    public int getActivationCounter() {
        return activationCounter;
    }

    /**
     * Set the value of the activation counter.
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
     * Set the {@link EnumMap} containing the previous output for each {@link Direction}.

     * 
     * @param neighborsPreviousInput the {@link EnumMap} containing the values to set.
     */
    public void setNeighborsPreviousInput(final EnumMap<Direction, Integer> neighborsPreviousInput) {
        this.neighborsPreviousInput = neighborsPreviousInput;
    }

}
