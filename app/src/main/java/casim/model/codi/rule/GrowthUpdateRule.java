package casim.model.codi.rule;

import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
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
                    final Direction gate = this.getNeuronGate(); 
                    builder.state(CellState.NEURON)
                           .gate(Optional.of(gate));
                    neighborsPreviousInput = RulesUtils.newFilledEnumMap(Signal.DENDRITE_SIGNAL.getValue());
                    neighborsPreviousInput.put(gate, Signal.AXON_SIGNAL.getValue());
                    neighborsPreviousInput.put(gate.getOpposite(), Signal.AXON_SIGNAL.getValue());
                    break;
                }
                int inputSum = RulesUtils.sumEnumMapValues(cell.getNeighborsPreviousInput());
                if (inputSum == 0) {
                    builder.state(CellState.BLANK);
                    break;
                }
                inputSum = RulesUtils.sumEnumMapSpecificValues(neighborsPreviousInput, Signal.AXON_SIGNAL.getValue());
                if (inputSum == Signal.AXON_SIGNAL.getValue()) {
                    final Direction direction = this.findSignalDirection(cell, Signal.AXON_SIGNAL).get();
                    builder.state(CellState.AXON)
                           .gate(Optional.of(direction)); 
                    this.fillEnumMapWithPredicate(neighborsPreviousInput, cell, Signal.DENDRITE_SIGNAL.getValue(), 0);
                    break;

                }
                if (inputSum > Signal.AXON_SIGNAL.getValue()) {
                    builder.state(CellState.BLANK);
                    neighborsPreviousInput = RulesUtils.newFilledEnumMap(0);
                    break;
                }
                inputSum = RulesUtils.sumEnumMapSpecificValues(neighborsPreviousInput, Signal.DENDRITE_SIGNAL.getValue());
                if (inputSum == Signal.DENDRITE_SIGNAL.getValue()) {
                    final Direction direction = this.findSignalDirection(cell, Signal.DENDRITE_SIGNAL).get();
                    builder.state(CellState.DENDRITE)
                           .gate(Optional.of(direction.getOpposite()));
                    this.fillEnumMapWithPredicate(neighborsPreviousInput, cell, Signal.DENDRITE_SIGNAL.getValue(), 0);
                    break;
                }
                builder.state(CellState.BLANK);
                neighborsPreviousInput = RulesUtils.newFilledEnumMap(0);
                break;
            case NEURON:
                builder.state(cell.getState())
                       .gate(cell.getGate());
                neighborsPreviousInput = RulesUtils.newFilledEnumMap(Signal.DENDRITE_SIGNAL.getValue());
                neighborsPreviousInput.put(cell.getGate().get(), Signal.AXON_SIGNAL.getValue());
                neighborsPreviousInput.put(cell.getOppositeToGate().get(), Signal.AXON_SIGNAL.getValue());
                break;
            case AXON:
                builder.state(cell.getState())
                       .gate(cell.getGate());
                this.fillEnumMapWithPredicate(neighborsPreviousInput, cell, Signal.AXON_SIGNAL.getValue(), 0);
                break;
            case DENDRITE:
                builder.state(cell.getState())
                       .gate(cell.getGate());
                this.fillEnumMapWithPredicate(neighborsPreviousInput, cell, Signal.DENDRITE_SIGNAL.getValue(), 0);
                break;
            default:
                break;
        }
        return builder.chromosome(cell.getChromosome())
                      .activationCounter(cell.getActivationCounter())
                      .neighborsPreviousInput(neighborsPreviousInput)
                      .build();
    }

    private void fillEnumMapWithPredicate(final EnumMap<Direction, Integer> neighborsPreviousInput,
            final CoDiCell cell, final int valueA, final int valueB) {
        for (final var d: Direction.values()) {
            if (neighborsPreviousInput.containsKey(d)) {
                neighborsPreviousInput.put(d, cell.getChromosome().get(d) ? valueA : valueB);
            }
        }
    }

    private Optional<Direction> findSignalDirection(final CoDiCell cell, final Signal signal) {
        return cell.getNeighborsPreviousInput().entrySet().stream().
                filter(e -> e.getValue() == signal.getValue()).
                map(e -> e.getKey()).
                findFirst();
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
