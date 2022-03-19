package casim.model.codi.rule;

import java.util.EnumMap;
import java.util.List;
import java.util.function.BiFunction;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.rule.AbstractUpdateRule;
import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.attributes.CellState;
import casim.model.codi.cell.attributes.Direction;
import casim.model.codi.cell.attributes.Signal;
import casim.model.codi.cell.builder.CoDiCellBuilder;
import casim.model.codi.cell.builder.CoDiCellBuilderImpl;
import casim.utils.coordinate.Coordinates3D;
import casim.utils.grid.Grid;

/**
 * Implementation of the {@link casim.model.abstraction.rule.UpdateRule} for {@link CoDi} growth phase.
 */
public class GrowthUpdateRule extends AbstractUpdateRule<Coordinates3D<Integer>, CoDiCell> {

    /**
     * Constructor of {@link SignalingUpdateRule}.
     * 
     * @param neighborsFunction it takes as input a pair {@link Coordinates3D}+{@link CoDiCell} and the {@link Grid} of the 
     *     automaton and return a list of pair {@link Coordinates3D}+{@link CoDiCell} of all the neighbors of the cell taken
     *     as input.
     */
    public GrowthUpdateRule(
            final BiFunction<Pair<Coordinates3D<Integer>, CoDiCell>, Grid<Coordinates3D<Integer>, CoDiCell>, List<Pair<Coordinates3D<Integer>, CoDiCell>>> neighborsFunction) {
        super(neighborsFunction);
    }

    @Override
    protected CoDiCell nextCell(final Pair<Coordinates3D<Integer>, CoDiCell> cellPair,
            final List<Pair<Coordinates3D<Integer>, CoDiCell>> neighborsPairs) {
        final CoDiCell cell = cellPair.getRight();
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        EnumMap<Direction, Integer> neighborsPreviousInput = cell.getNeighborsPreviousInput();
        switch (cell.getState()) {
            case BLANK:
                if (this.isNeuronSeed(cellPair.getLeft())) {
                    builder.state(CellState.NEURON);
                    final Direction gate = this.getNeuronGate(); 
                    neighborsPreviousInput = RulesUtils.newFilledEnumMap(Signal.DENDRITE_SIGNAL.getValue());
                    neighborsPreviousInput.put(gate, Signal.AXON_SIGNAL.getValue());
                    neighborsPreviousInput.put(gate.getOpposite(), Signal.AXON_SIGNAL.getValue());
                    break;
                }
                int inputSum = RulesUtils.sumEnumMapValues(cell.getNeighborsPreviousInput());
                if (inputSum == 0) {
                    break;
                }
                inputSum = RulesUtils.sumEnumMapSpecificValues(neighborsPreviousInput, Signal.AXON_SIGNAL.getValue());
                if (inputSum == Signal.AXON_SIGNAL.getValue()) {
                    builder.state(CellState.AXON);
                    final Direction direction = cell.getNeighborsPreviousInput().entrySet().stream().
                        filter(e -> e.getValue() == Signal.AXON_SIGNAL.getValue()).
                        map(e -> e.getKey()).
                        findFirst().get();
                    builder.gate(direction); 
                    for (final var d: Direction.values()) {
                        neighborsPreviousInput.put(d, cell.getChromosome().get(d) ? Signal.AXON_SIGNAL.getValue() : 0);
                    }
                    break;

                }
                if (inputSum > Signal.AXON_SIGNAL.getValue()) {
                    neighborsPreviousInput = RulesUtils.newFilledEnumMap(0);
                    break;
                }
                inputSum = RulesUtils.sumEnumMapSpecificValues(neighborsPreviousInput, Signal.DENDRITE_SIGNAL.getValue());
                if (inputSum == Signal.DENDRITE_SIGNAL.getValue()) {
                    builder.state(CellState.DENDRITE);
                    final Direction direction = cell.getNeighborsPreviousInput().entrySet().stream().
                            filter(e -> e.getValue() == Signal.DENDRITE_SIGNAL.getValue()).
                            map(e -> e.getKey()).
                            findFirst().get();
                    builder.gate(direction.getOpposite());
                    for (final var d: Direction.values()) {
                        neighborsPreviousInput.put(d, cell.getChromosome().get(d) ? Signal.DENDRITE_SIGNAL.getValue() : 0);
                    }
                    break;
                }
                builder.state(cell.getState());
                neighborsPreviousInput = RulesUtils.newFilledEnumMap(0);
                break;
            case NEURON:
                neighborsPreviousInput = RulesUtils.newFilledEnumMap(Signal.DENDRITE_SIGNAL.getValue());
                neighborsPreviousInput.put(cell.getGate().get(), Signal.AXON_SIGNAL.getValue());
                neighborsPreviousInput.put(cell.getOppositeToGate().get(), Signal.AXON_SIGNAL.getValue());
                break;
            case AXON:
                for (final var d: Direction.values()) {
                    neighborsPreviousInput.put(d, cell.getChromosome().get(d) ? Signal.AXON_SIGNAL.getValue() : 0);
                }
                break;
            case DENDRITE:
                for (final var d: Direction.values()) {
                    neighborsPreviousInput.put(d, cell.getChromosome().get(d) ? Signal.DENDRITE_SIGNAL.getValue() : 0);
                }
                break;
            default:
                break;
        }
        builder.activationCounter(cell.getActivationCounter());
        builder.neighborsPreviousInput(neighborsPreviousInput);
        return builder.build();
    }

    private Direction getNeuronGate() {
        Direction direction = Direction.getRandomDirection();
        while (direction == Direction.TOP || direction == Direction.BOTTOM) {
            direction = Direction.getRandomDirection();
        }
        return direction;
    }

    private boolean isNeuronSeed(final Coordinates3D<Integer> coord) {
        return RulesUtils.rand25() && RulesUtils.rand25() 
                && coord.getY() % 2 + coord.getZ() % 2 == 0;
    }

}
