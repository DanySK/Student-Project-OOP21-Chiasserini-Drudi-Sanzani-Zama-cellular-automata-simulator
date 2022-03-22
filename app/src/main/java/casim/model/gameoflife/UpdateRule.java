package casim.model.gameoflife;

import java.util.List;
import java.util.function.BiFunction;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.rule.AbstractUpdateRule;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.grid.Grid;

/**
 * The {@link casim.model.gameoflife.GameOfLife}'s rule used to update the {@link casim.model.gameoflife.GameOfLifeCell}'s state.
 * 
 * @param Coordinates2D<Integer> the {@link Coordinates2D} of the {@link GameOfLifeCell}.
 * @param GameOfLifeCell the cell to update.
 */
public class UpdateRule extends AbstractUpdateRule<Coordinates2D<Integer>, GameOfLifeCell> {

    public UpdateRule(BiFunction<Pair<Coordinates2D<Integer>, GameOfLifeCell>, Grid<Coordinates2D<Integer>, GameOfLifeCell>, List<Pair<Coordinates2D<Integer>, GameOfLifeCell>>> neighborsFunction) {
        super(neighborsFunction);
    }

    /**
     * Function that analize the neighbors of the {@link Cell} and calculates his next state.
     * 
     * @param cellPair a {@link Pair} that contains the {@link GameOfLifeCell} and his {@link Coordinates2D}.
     * @param neighborsPairs a {@link List} of all the {@lonk GameOfLifeCell} neighbors and hsi {@link Coordinates2D}.
     */
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

    /**
     * Utility function for count the number of neighbors alive of one {@link Cell}.
     * 
     * @param neighborsPairs an {@link Iterable} {@link Pair} of {@link GameOfLifeCell} and his {@link Coordinates2D}.
     */
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