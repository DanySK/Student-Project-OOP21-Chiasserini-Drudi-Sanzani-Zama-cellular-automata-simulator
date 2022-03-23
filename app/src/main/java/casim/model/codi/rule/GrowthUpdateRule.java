package casim.model.codi.rule;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.rule.AbstractUpdateRule;
import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.attributes.CellState;
import casim.model.codi.cell.attributes.Direction;
import casim.model.codi.cell.attributes.Signal;
import casim.model.codi.cell.builder.CoDiCellBuilder;
import casim.model.codi.cell.builder.CoDiCellBuilderImpl;
import casim.model.codi.utils.CodiUtils;
import casim.utils.coordinate.Coordinates3D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid;

/**
 * Implementation of the {@link casim.model.abstraction.rule.UpdateRule} for {@link CoDi} growth phase.
 */
public class GrowthUpdateRule extends AbstractUpdateRule<Coordinates3D<Integer>, CoDiCell> {

    private static final int NEURON_GENERATION_PROBABILITY = 10;

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
        CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        switch (cell.getState()) {
            case BLANK:
                builder = this.blankCellGrowth(cell, cellPair.getKey(), neighborsPairs);
                break;
            case NEURON:
                builder = this.neuronCellGrowth(cell);
                break;
            case AXON:
                builder = this.axonCellGrowth(cell); 
                break;
            case DENDRITE:
                builder = this.dendriteCellGrowth(cell);
                break;
            default:
                break;
        }
        return builder.chromosome(cell.getChromosome())
                      .activationCounter(cell.getActivationCounter())
                      .build();
    }

    private CoDiCellBuilder blankCellGrowth(final CoDiCell cell, final Coordinates3D<Integer> cellCoord,
            final List<Pair<Coordinates3D<Integer>, CoDiCell>> neighborsPairs) {
        if (this.isNeuronSeed(cellCoord)) {
            return this.blankToNeuron(cellCoord, neighborsPairs);
        }
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        EnumMap<Direction, Integer> neighborsPreviousInput = cell.getNeighborsPreviousInput();
        int inputSum = CodiUtils.sumEnumMapValues(cell.getNeighborsPreviousInput());
        if (inputSum == 0) {
            builder.state(CellState.BLANK);
            return builder.neighborsPreviousInput(neighborsPreviousInput);
        }
        inputSum = CodiUtils.sumEnumMapSpecificValues(neighborsPreviousInput, Signal.AXON_SIGNAL.getValue());
        if (inputSum == Signal.AXON_SIGNAL.getValue()) {
            return this.blankToAxon(cell);
        }
        if (inputSum > Signal.AXON_SIGNAL.getValue()) {
            builder.state(CellState.BLANK);
            neighborsPreviousInput = CodiUtils.newFilledEnumMap(() -> 0);
            return builder.neighborsPreviousInput(neighborsPreviousInput);
        }
        inputSum = CodiUtils.sumEnumMapSpecificValues(neighborsPreviousInput, Signal.DENDRITE_SIGNAL.getValue());
        if (inputSum == Signal.DENDRITE_SIGNAL.getValue()) {
            return this.blankToDendrite(cell);
        }
        builder.state(CellState.BLANK);
        neighborsPreviousInput = CodiUtils.newFilledEnumMap(() -> 0);
        return builder.neighborsPreviousInput(neighborsPreviousInput);
    }

    private CoDiCellBuilder blankToNeuron(final Coordinates3D<Integer> cellCoord,
            final List<Pair<Coordinates3D<Integer>, CoDiCell>> neighborsPairs) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final List<Coordinates3D<Integer>> neighborsCoordinates = neighborsPairs.stream()
                .map(p -> p.getLeft()).collect(Collectors.toList());
        final Direction gate = this.getNeuronGate(cellCoord, neighborsCoordinates); 
        builder.state(CellState.NEURON)
               .gate(Optional.of(gate));
        final EnumMap<Direction, Integer> neighborsPreviousInput = CodiUtils.newFilledEnumMap(() -> Signal.DENDRITE_SIGNAL.getValue());
        neighborsPreviousInput.put(gate, Signal.AXON_SIGNAL.getValue());
        neighborsPreviousInput.put(gate.getOpposite(), Signal.AXON_SIGNAL.getValue());
        return builder.neighborsPreviousInput(neighborsPreviousInput);
    }

    private CoDiCellBuilder blankToAxon(final CoDiCell cell) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final Direction direction = this.findSignalDirection(cell, Signal.AXON_SIGNAL).get();
        builder.state(CellState.AXON)
               .gate(Optional.of(direction));
        final EnumMap<Direction, Integer> neighborsPreviousInput = CodiUtils.conditionalFillNeighborsPreviosInput(cell, Signal.DENDRITE_SIGNAL.getValue(), 0);
        return builder.neighborsPreviousInput(neighborsPreviousInput);
    }

    private CoDiCellBuilder blankToDendrite(final CoDiCell cell) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final Direction direction = this.findSignalDirection(cell, Signal.DENDRITE_SIGNAL).get();
        builder.state(CellState.DENDRITE)
               .gate(Optional.of(direction.getOpposite()));
        final EnumMap<Direction, Integer> neighborsPreviousInput = CodiUtils.conditionalFillNeighborsPreviosInput(cell, Signal.DENDRITE_SIGNAL.getValue(), 0);
        return builder.neighborsPreviousInput(neighborsPreviousInput);
    }

    private CoDiCellBuilder neuronCellGrowth(final CoDiCell cell) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        builder.state(cell.getState())
            .gate(cell.getGate());
        final EnumMap<Direction, Integer> neighborsPreviousInput = CodiUtils.newFilledEnumMap(() -> Signal.DENDRITE_SIGNAL.getValue());
        neighborsPreviousInput.put(cell.getGate().get(), Signal.AXON_SIGNAL.getValue());
        neighborsPreviousInput.put(cell.getOppositeToGate().get(), Signal.AXON_SIGNAL.getValue());
        return builder.neighborsPreviousInput(neighborsPreviousInput);
    }

    private CoDiCellBuilder axonCellGrowth(final CoDiCell cell) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        builder.state(cell.getState())
            .gate(cell.getGate());
        final EnumMap<Direction, Integer> neighborsPreviousInput = CodiUtils.conditionalFillNeighborsPreviosInput(cell, Signal.AXON_SIGNAL.getValue(), 0);
        return builder.neighborsPreviousInput(neighborsPreviousInput);
    }

    private CoDiCellBuilder dendriteCellGrowth(final CoDiCell cell) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        builder.state(cell.getState())
        .gate(cell.getGate());
        final EnumMap<Direction, Integer> neighborsPreviousInput = CodiUtils.conditionalFillNeighborsPreviosInput(cell, Signal.DENDRITE_SIGNAL.getValue(), 0);
        return builder.neighborsPreviousInput(neighborsPreviousInput);
    }

    private Optional<Direction> findSignalDirection(final CoDiCell cell, final Signal signal) {
        return cell.getNeighborsPreviousInput().entrySet().stream().
                filter(e -> e.getValue() == signal.getValue()).
                map(Map.Entry::getKey).
                findFirst();
    }

    private Direction getNeuronGate(final Coordinates3D<Integer> cellCoord,
            final List<Coordinates3D<Integer>> neighborsCoordinates) {
        Direction direction = Direction.getRandomDirection();
        while (direction == Direction.EAST || direction == Direction.WEST
                || !neighborsCoordinates.contains(CoordinatesUtil.sumInt(cellCoord, direction.getDirectionOffset()))) {
            direction = Direction.getRandomDirection();
        }
        return direction;
    }

    private boolean isNeuronSeed(final Coordinates3D<Integer> coord) {
        return CodiUtils.booleanWithSpecificProbability(NEURON_GENERATION_PROBABILITY) 
                    && coord.getY() % 2 + coord.getZ() % 2 == 0;
    }

}
