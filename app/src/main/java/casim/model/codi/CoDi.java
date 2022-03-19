package casim.model.codi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.model.abstraction.utils.NeighborsFunctions;
import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.CoDiCellSupplier;
import casim.model.codi.cell.attributes.CellState;
import casim.model.codi.cell.attributes.Direction;
import casim.model.codi.cell.builder.CoDiCellBuilder;
import casim.model.codi.cell.builder.CoDiCellBuilderImpl;
import casim.model.codi.rule.GrowthUpdateRule;
import casim.model.codi.rule.SignalingUpdateRule;
import casim.utils.coordinate.Coordinates3D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid2DImpl;
import casim.utils.grid.Grid3D;
import casim.utils.grid.Grid3DImpl;
import casim.utils.range.Ranges;

/**
 * Implementation of CoDi {@link casim.model.abstraction.automaton.Automaton}.
 */
public class CoDi extends AbstractAutomaton<CellState, CoDiCell> {

    private boolean changed; 
    private Grid3D<CoDiCell> state;
    private boolean hasSetupSignaling;
    private final GrowthUpdateRule growthUpdateRule;
    private final SignalingUpdateRule signalingUpdateRule;


    /**
     * Constructor of {@link CoDi}.
     * 
     * @param state the grid of {@link CellState} used to initialize the automaton.
     */
    public CoDi(final Grid3D<CellState> state) {
        final Supplier<CoDiCell> cellSupplier = new CoDiCellSupplier();
        this.changed = true;
        this.hasSetupSignaling = false;
        this.state = state.map(s -> cellSupplier.get());
        this.growthUpdateRule = new GrowthUpdateRule(NeighborsFunctions::neighbors3DFunction);
        this.signalingUpdateRule = new SignalingUpdateRule(NeighborsFunctions::neighbors3DFunction);
        this.restrictToGrid();
    }

    private void restrictToGrid() {
        for (final var coord: this.visitGrid()) {
            final CoDiCell cell = this.normalize(this.state.get(coord), coord);
            this.state.set(coord, cell);
        }
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    protected Grid2D<CoDiCell> doStep() {
        if (changed) {
            this.changed = false;
            return growthStep();
        } else {
            if (!hasSetupSignaling) {
                signalingSetup();
            }
            return signalStep();
        }
    }

    private Grid2D<CoDiCell> growthStep() { //TODO si potrebbe mettere un CopyOf in Grid
        final var newState = new Grid3DImpl<CoDiCell>(this.state.getHeight(), this.state.getWidth(), this.state.getDepth());
        for (final var coord: this.visitGrid()) {
            CoDiCell cell = this.state.get(coord);
            final CellState oldCellState = cell.getState();
            cell = this.growthUpdateRule.getNextCell(Pair.of(coord, cell), this.state); 
            cell = this.normalize(cell, coord);
            if (oldCellState != cell.getState()) {
                this.changed = true;
            }
            newState.set(coord, cell);
        }
        this.state = newState;
        return this.getGrid();
    }

    private Grid2D<CoDiCell> signalStep() {
        final var newState = new Grid3DImpl<CoDiCell>(this.state.getHeight(), this.state.getWidth(), this.state.getDepth());
        for (final var coord: this.visitGrid()) {
            CoDiCell cell = this.signalingUpdateRule.getNextCell(Pair.of(coord, this.state.get(coord)), this.state);
            cell = this.normalize(cell, coord);
            newState.set(coord, cell);
        }
        this.state = newState;
        return this.getGrid();
    }

    private void signalingSetup() {
        final Random random = new Random(); 
        this.hasSetupSignaling = true;
        for (final var coord: this.visitGrid()) {
            final var cell = this.state.get(coord);
            if (cell.getState() == CellState.NEURON) { //TODO do not use setter!
                cell.setActivationCounter(random.nextInt(32));
            } else {
                cell.setActivationCounter(0);
            }
        }
    }

    private List<Coordinates3D<Integer>> visitGrid() {
        final List<Coordinates3D<Integer>> coordList = new ArrayList<>();
        for (final var x: Ranges.of(0, this.state.getHeight())) {
            for (final var y: Ranges.of(0, this.state.getWidth())) {
                for (final var z: Ranges.of(0, this.state.getDepth())) {
                    final var coord = CoordinatesUtil.of(x, y, z);
                    coordList.add(coord);
                }
            }
        }
        return coordList;
    }

    private CoDiCell normalize(final CoDiCell cell, final Coordinates3D<Integer> coord) {
        final List<Direction> directionsToRemove = new ArrayList<>();
        if (coord.getX() <= 0) {
            directionsToRemove.add(Direction.WEST);
        }
        if (coord.getX() >= this.state.getWidth() - 1) {
            directionsToRemove.add(Direction.BOTTOM);
        }
        if (coord.getY() <= 0) {
            directionsToRemove.add(Direction.WEST);
        }
        if (coord.getY() >= this.state.getHeight() - 1) {
            directionsToRemove.add(Direction.TOP);
        }
        if (coord.getZ() <= 0) {
            directionsToRemove.add(Direction.SOUTH);
        }
        if (coord.getZ() >= this.state.getDepth() - 1) {
            directionsToRemove.add(Direction.NORTH);
        }
        return this.removeDirectionFromNeighbors(cell, directionsToRemove);
    }

    private CoDiCell removeDirectionFromNeighbors(final CoDiCell cell, final List<Direction> directions) {
        if (directions.isEmpty()) {
            return cell;
        }
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final var neighborsPreviousInput = cell.getNeighborsPreviousInput();
        final var chromosome = cell.getChromosome();
        for (final var d: directions) {
            neighborsPreviousInput.remove(d);
            chromosome.remove(d);
        }
        builder.state(cell.getState());
        builder.chromosome(chromosome);
        builder.activationCounter(cell.getActivationCounter());
        builder.neighborsPreviousInput(neighborsPreviousInput);
        return builder.build();
    }

    @Override
    public Grid2D<CoDiCell> getGrid() {
        final int x = 0; //TODO per ora tengo un layer costante
        final Grid2D<CoDiCell> gridLayer = new Grid2DImpl<>(this.state.getWidth(), this.state.getDepth());
        for (final var y: Ranges.of(0, this.state.getWidth())) {
            for (final var z: Ranges.of(0, this.state.getDepth())) {
                final var coord2D = CoordinatesUtil.of(y, z);
                final var coord3D = CoordinatesUtil.of(x, y, z);
                CoDiCell cell = this.state.get(coord3D);
                if ((cell.getState() == CellState.AXON || cell.getState() == CellState.DENDRITE) 
                        && cell.getActivationCounter() != 0) {
                    cell = this.getActiveCell(cell);
                }
                gridLayer.set(coord2D, cell);
            }
        }
        return gridLayer;
    }

    private CoDiCell getActiveCell(final CoDiCell cell) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        builder.gate(cell.getGate().get());
        builder.activationCounter(cell.getActivationCounter());
        builder.chromosome(cell.getChromosome());
        builder.neighborsPreviousInput(cell.getNeighborsPreviousInput());
        builder.state((cell.getState() == CellState.AXON) ? CellState.ACTIVATED_AXON : CellState.ACTIVATED_DENDRITE);
        return builder.build();
    }

}
