package casim.model.codi.cell.builder;

import java.util.EnumMap;
import java.util.Optional;

import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.attributes.CellState;
import casim.model.codi.cell.attributes.Direction;

/**
 * Implementation of {@link CoDiCellBuilder}.
 */
public class CoDiCellBuilderImpl implements CoDiCellBuilder {

    private boolean built;
    private Optional<CellState> state;
    private Optional<Direction> gate;
    private Optional<Integer> activationCounter;
    private Optional<EnumMap<Direction, Boolean>> chromosome;
    private Optional<EnumMap<Direction, Integer>> neighborsPreviousInput;

    /**
     * Construct a {@link CoDiCellBuilder} with initial values.
     */
    public CoDiCellBuilderImpl() {
        this.built = false;
        this.gate = Optional.empty();
        this.state = Optional.empty();
        this.activationCounter = Optional.empty();
        this.neighborsPreviousInput = Optional.empty();
    }

    private void check(final boolean value) {
        if (!value) {
            throw new IllegalStateException();
        }
    }

    @Override
    public CoDiCellBuilder gate(final Optional<Direction> gate) {
        this.check(!built);
        this.gate = gate;
        return this;
    }

    @Override
    public CoDiCellBuilder state(final CellState state) {
        this.check(!built);
        this.state = Optional.of(state);
        return this;
    }

    @Override
    public CoDiCellBuilder chromosome(final EnumMap<Direction, Boolean> chromosome) {
        this.chromosome = Optional.of(chromosome);
        return this;
    }

    @Override
    public CoDiCellBuilder activationCounter(final int activationCounter) {
        this.check(!built);
        this.activationCounter = Optional.of(activationCounter);
        return this;
    }

    @Override
    public CoDiCellBuilder neighborsPreviousInput(final EnumMap<Direction, Integer> neighborsPreviousInput) {
        this.check(!built);
        this.neighborsPreviousInput = Optional.of(neighborsPreviousInput);
        return this;
    }

    @Override
    public CoDiCell build() { 
        this.check(!built);
        this.check(this.state.isPresent());
        this.check(this.chromosome.isPresent());
        this.check(this.activationCounter.isPresent());
        this.check(this.neighborsPreviousInput.isPresent());
        this.built = true;
        return new CoDiCell(this.state.get(), this.activationCounter.get(), this.gate,
                                this.neighborsPreviousInput.get(), this.chromosome.get());
    }

}
