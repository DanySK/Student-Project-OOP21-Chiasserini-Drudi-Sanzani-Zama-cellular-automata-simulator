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
        this(state, 0, new EnumMap<>(Direction.class), Optional.empty());
    }

    /**
     * Constructor a {@link CoDiCell} with specific initial values.
     * 
     * @param state the {@link CellState} of the cell.
     * @param activationCounter the value of the activation counter.
     * @param gate the gate of the cell (an empty optional if it's not defined).
     * @param neighborsPriorInput the {@link EnumMap} containing the values of the prior inputs.
     */
    public CoDiCell(final CellState state, final int activationCounter, 
            final EnumMap<Direction, Integer> neighborsPriorInput, final Optional<Direction> gate) {
        super(state);
        this.gate = gate;
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
     * The current value of the activation counter, the cell can transmit the signal only if a predetermined value is reached.
     * 
     * @return the current value of the activation counter.
     */
    public int getActivationCounter() {
        return activationCounter;
    }

    /**
     * Return an {@link EnumMap} containing the previous output for each {@link Direction}.
     * 
     * @return Return an {@link EnumMap} containing the previous output for each {@link Direction}.
     */
    public EnumMap<Direction, Integer> getNeighborsPreviousInput() {
        return neighborsPreviousInput;
    }

}
