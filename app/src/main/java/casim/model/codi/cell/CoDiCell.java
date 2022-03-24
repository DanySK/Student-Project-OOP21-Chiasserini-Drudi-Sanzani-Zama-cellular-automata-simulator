package casim.model.codi.cell;

import java.util.EnumMap;
import java.util.Optional;

import casim.model.abstraction.cell.AbstractCell;
import casim.model.codi.cell.attributes.CellState;
import casim.model.codi.cell.attributes.Direction;

/**
 * The CoDi cells.
 */
public class CoDiCell extends AbstractCell<CellState> {

    private int activationCounter;
    private final Optional<Direction> gate;
    private final EnumMap<Direction, Boolean> chromosome; //TODO se uso hashmap posso tornare copie difensive.
    private final EnumMap<Direction, Integer> neighborsPreviousInput;

    /**
     * Construct a new  {@link CoDiCell} with specific initial values.
     * 
     * @param state the {@link CellState} of the cell.
     * @param activationCounter the value of the activation counter.
     * @param gate the gate of the cell (an empty optional if it's not defined).
     * @param neighborsPreviousInput the {@link EnumMap} containing the values of the prior inputs.
     * @param chromosome the {@link EnumMap} containing the growth instruction.
     */
    public CoDiCell(final CellState state, final int activationCounter, final Optional<Direction> gate, 
            final EnumMap<Direction, Integer> neighborsPreviousInput, final EnumMap<Direction, Boolean> chromosome) {
        super(state);
        this.gate = gate;
        this.chromosome = chromosome;
        this.activationCounter = activationCounter;
        this.neighborsPreviousInput = neighborsPreviousInput;
    }

    /**
     * Return the gate of the {@link CoDiCell}, it's meaning depends from the {@link CellState} of the cell:
     *  Neuron -> direction where the axon point to;
     *  Axon -> direction where it receives the signal;
     *  Dendrite -> direction where it transmits the signal.
     *
     * @return an {@link Optional} which contain the {@link Direction} of the gate,
     *  it's empty if it's not already defined.
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
     * The current value of the activation counter, 
     * the cell can transmit the signal only if a predetermined value is reached.
     * 
     * @return the current value of the activation counter.
     */
    public int getActivationCounter() {
        return activationCounter;
    }

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
     * Set the value in the {@link Direction} take as input,
     * if the {@link EnumMap} doesn't contains the direction it does nothing.
     * 
     * @param direction the direction where set the value.
     * @param value the value to set.
     */
    public void setNeighborsPreviousInputDirection(final Direction direction, final int value) {
        if (this.neighborsPreviousInput.containsKey(direction)) {
            this.neighborsPreviousInput.put(direction, value);
        }
    }

    /**
     * Return an {@link Optional} containing the value in the {@link Direction} take as input,
     * an empty optional if the direction is not present.
     * 
     * @param direction the direction of the value to return.
     * @return an {@link Optional} containing the value in the direction take as input,
     * an empty optional if it's not present.
     */
    public Optional<Integer> getSpecificNeighborsPreviousInput(final Direction direction) {
        return this.neighborsPreviousInput.containsKey(direction) 
                ? Optional.of(this.neighborsPreviousInput.get(direction)) : Optional.empty();
    }

    /**
     * Return a {@link EnumMap} describing the chromosome of the cell.
     * The value is true in the directions where the cell can send the signal.
     * 
     * @return Return a {@link EnumMap} representing the chromosome of the cell.
     */
    public EnumMap<Direction, Boolean> getChromosome() {
        return this.chromosome;
    }

}
