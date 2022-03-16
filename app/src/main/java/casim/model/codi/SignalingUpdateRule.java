package casim.model.codi;

import java.util.List;
import java.util.function.BiFunction;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.rule.AbstractUpdateRule;
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
        switch (cell.getState()) {
            case BLANK:
                break;
            case NEURON:
                inputCounter = 1 + cell.getNeighborsPriorInput().values().stream().reduce((n1, n2) -> n1 + n2).get()
                        - cell.getNeighborsPriorInput().get(cell.getOppositeToGate().get())
                        - cell.getNeighborsPriorInput().get(cell.getGate().get());
                cell.setActivationCounter(inputCounter);
                this.setPriorInputToValue(cell, 0);
                if (cell.getActivationCounter() > NEURON_ACTIVATION_VALUE) {
                   cell.getNeighborsPriorInput().put(cell.getGate().get(), 1);
                   cell.getNeighborsPriorInput().put(cell.getOppositeToGate().get(), 1);
                   cell.setActivationCounter(0);
                }
                break;
            case AXON:
                this.setPriorInputToValue(cell, cell.getActivationCounter());
                cell.setActivationCounter((cell.getNeighborsPriorInput().get(cell.getGate().get()) != 0) ? 1 : 0);
                break;
            case DENTRITE:
                inputCounter = cell.getNeighborsPriorInput().values().stream().reduce((n1, n2) -> n1 + n2).get();
                inputCounter = inputCounter > 2 ? 2 : inputCounter;
                this.setPriorInputToValue(cell, 0);
                cell.getNeighborsPriorInput().put(cell.getGate().get(), inputCounter);
                cell.setActivationCounter((inputCounter != 0) ? 1 : 0);
                break;
            default:
                break;
            }
        return null; //TODO don't change the cell value but return the new cell
    }

    private void setPriorInputToValue(final CoDiCell cell, final int value) {
        for (final var d: Direction.values()) {
            cell.getNeighborsPriorInput().put(d, value);
        }
    }

}
