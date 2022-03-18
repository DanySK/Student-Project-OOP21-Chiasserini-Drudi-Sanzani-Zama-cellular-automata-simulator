package casim.model.codi.rule;

import java.util.EnumMap;
import java.util.List;
import java.util.function.BiFunction;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.rule.AbstractUpdateRule;
import casim.model.codi.Direction;
import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.builder.CoDiCellBuilder;
import casim.model.codi.cell.builder.CoDiCellBuilderImpl;
import casim.utils.coordinate.Coordinates3D;
import casim.utils.grid.Grid;

/**
 * Implementation of the {@link casim.model.abstraction.rule.UpdateRule} for {@link CoDi} automaton.
 */
public class SignalingUpdateRule extends AbstractUpdateRule<Coordinates3D<Integer>, CoDiCell> {

    private static final int NEURON_ACTIVATION_VALUE = 31;

    /**
     * Constructor of {@link SignalingUpdateRule}.
     * 
     * @param neighborsFunction it takes as input a pair {@link Coordinates3D}+{@link CoDiCell} and the {@link Grid} of the 
     *     automaton and return a list of pair {@link Coordinates3D}+{@link CoDiCell} of all the neighbors of the cell taken
     *     as input.
     */
    public SignalingUpdateRule(
            final BiFunction<Pair<Coordinates3D<Integer>, CoDiCell>, Grid<Coordinates3D<Integer>, CoDiCell>, List<Pair<Coordinates3D<Integer>, CoDiCell>>> neighborsFunction) {
        super(neighborsFunction);
    }

    @Override
    protected CoDiCell nextCell(final Pair<Coordinates3D<Integer>, CoDiCell> cellPair,
            final List<Pair<Coordinates3D<Integer>, CoDiCell>> neighborsPairs) {
        int inputCounter;
        final CoDiCell cell = cellPair.getRight();
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        EnumMap<Direction, Integer> neighborsPreviousInput = this.fillEnumMap(0);
        builder.state(cell.getState());
        switch (cell.getState()) {
            case BLANK:
                builder.activationCounter(cell.getActivationCounter());
                break;
            case NEURON:
                inputCounter = 1 + sumPreviousInput(cell.getNeighborsPreviousInput())
                        - cell.getNeighborsPreviousInput().get(cell.getOppositeToGate().get())
                        - cell.getNeighborsPreviousInput().get(cell.getGate().get());
                builder.activationCounter(inputCounter);
                neighborsPreviousInput = this.fillEnumMap(0);
                if (cell.getActivationCounter() > NEURON_ACTIVATION_VALUE) {
                    neighborsPreviousInput.put(cell.getGate().get(), 1);
                    neighborsPreviousInput.put(cell.getOppositeToGate().get(), 1);
                    builder.activationCounter(0);
                }
                break;
            case AXON:
                neighborsPreviousInput = this.fillEnumMap(cell.getActivationCounter());
                builder.activationCounter((cell.getNeighborsPreviousInput().get(cell.getGate().get()) != 0) ? 1 : 0);
                break;
            case DENTRITE:
                inputCounter = sumPreviousInput(cell.getNeighborsPreviousInput());
                inputCounter = inputCounter > 2 ? 2 : inputCounter;
                neighborsPreviousInput = this.fillEnumMap(0);
                neighborsPreviousInput.put(cell.getGate().get(), inputCounter);
                builder.activationCounter((inputCounter != 0) ? 1 : 0);
                break;
            default:
                break;
            }
        builder.neighborsPreviousInput(neighborsPreviousInput);
        return builder.build();
    }

    private EnumMap<Direction, Integer> fillEnumMap(final int value) {
        final EnumMap<Direction, Integer> neighborsPreviousInput = new EnumMap<>(Direction.class);
        for (final var d: Direction.values()) {
            neighborsPreviousInput.put(d, value);
        }
        return neighborsPreviousInput;
    }

    private int sumPreviousInput(final EnumMap<Direction, Integer> neighborsPreviousInput) {
        return neighborsPreviousInput.values().stream().reduce((n1, n2) -> n1 + n2).get();
    }

}
