package casim.model.bryansbrain;

import java.util.List;
import java.util.function.BiFunction;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.rule.AbstractUpdateRule;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.grid.Grid;

public class UpdateRule extends AbstractUpdateRule<Coordinates2D<Integer>, BryansBrainCell> {

    private static final String UNKNOWN_STATE = "Unknown state.";
    private static final int ALIVE_NEIGHBOUR_BIRTH_VALUE = 2;

    public UpdateRule(BiFunction<Pair<Coordinates2D<Integer>, BryansBrainCell>, Grid<Coordinates2D<Integer>, BryansBrainCell>, List<Pair<Coordinates2D<Integer>, BryansBrainCell>>> neighborsFunction) {
        super(neighborsFunction);
    }

    @Override
    protected BryansBrainCell nextCell(Pair<Coordinates2D<Integer>, BryansBrainCell> cellPair,
            List<Pair<Coordinates2D<Integer>, BryansBrainCell>> neighborsPairs) {

        // TODO: System.out.println("Cell: " + cellPair.getLeft());
        // System.out.println("Neighbours: " + neighborsPairs.stream().map(x -> Pair.of(x.getLeft(), x.getRight().getState())).collect(Collectors.toList()));
        
        switch(cellPair.getRight().getState()) {
            case ALIVE:
                return new BryansBrainCell(CellState.DYING);
            case DYING:
                return new BryansBrainCell(CellState.DEAD);
            case DEAD:
                int aliveCells = countAliveNeighbors(neighborsPairs);
                // TODO: System.out.println(aliveCells);
                return new BryansBrainCell(
                    aliveCells == ALIVE_NEIGHBOUR_BIRTH_VALUE ? CellState.ALIVE : CellState.DEAD);
            default:
                throw new IllegalStateException(UNKNOWN_STATE);
        }
    }

    private int countAliveNeighbors(Iterable<Pair<Coordinates2D<Integer>, BryansBrainCell>> neighborsPairs) {
        int count = 0;
        for (final var neighbor : neighborsPairs) {
            if (neighbor.getRight().getState() == CellState.ALIVE) {
                count++;
            }
        }
        return count;
    }
}
