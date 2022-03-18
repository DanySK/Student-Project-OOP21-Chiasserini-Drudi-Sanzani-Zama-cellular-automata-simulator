package casim.model.codi;

import java.util.EnumMap;
import java.util.Optional;

/**
 * Implementation of {@link CoDiCellBuilder}.
 */
public class CoDiCellBuilderImpl implements CoDiCellBuilder {

    private boolean built;
    private Optional<CellState> state;
    private Optional<Integer> activationCounter;
    private Optional<EnumMap<Direction, Integer>> neighborsPreviousInput;

    /**
     * Construct a {@link CoDiCellBuilder}.
     */
    public CoDiCellBuilderImpl() {
        this.built = false;
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
    public CoDiCellBuilder state(final CellState state) {
        this.check(!built);
        this.state = Optional.of(state);
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
        this.check(this.activationCounter.isPresent());
        this.check(this.neighborsPreviousInput.isPresent());
        this.built = true;
        return new CoDiCell(this.state.get(), this.activationCounter.get(), this.neighborsPreviousInput.get());
    }

}
