package casim.model.gameoflife;

import java.util.List;
import java.util.function.BiFunction;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.rule.AbstractUpdateRule;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.grid.Grid;

//TODO javadoc
public class UpdateRule extends AbstractUpdateRule<Coordinates2D<Integer>, GameOfLifeCell> {

    public UpdateRule(BiFunction<Pair<Coordinates2D<Integer>, GameOfLifeCell>, Grid<Coordinates2D<Integer>, GameOfLifeCell>, List<Pair<Coordinates2D<Integer>, GameOfLifeCell>>> neighborsFunction) {
        super(neighborsFunction);
    }

    @Override
    protected GameOfLifeCell nextCell(Pair<Coordinates2D<Integer>, GameOfLifeCell> cellPair,
        List<Pair<Coordinates2D<Integer>, GameOfLifeCell>> neighborsPairs) {
        
        int aliveCells = this.countAliveNeighbors(neighborsPairs);

        if(cellPair.getRight().getState() == GameOfLifeState.ALIVE) {
            if(aliveCells == 2 || aliveCells == 3) {
                return new GameOfLifeCell(GameOfLifeState.ALIVE);
            } else {
                return new GameOfLifeCell(GameOfLifeState.DEAD);
            }
        } else {
            if(aliveCells == 3) {
                return new GameOfLifeCell(GameOfLifeState.ALIVE);
            } else {
                return new GameOfLifeCell(GameOfLifeState.DEAD);
            }
        }
    }

    private int countAliveNeighbors(Iterable<Pair<Coordinates2D<Integer>, GameOfLifeCell>> neighborsPairs) {
        int count = 0;
        for (final var neighbor : neighborsPairs) {
            if (neighbor.getRight().getState() == GameOfLifeState.ALIVE) {
                count++;
            }
        }
        return count;
    }
}