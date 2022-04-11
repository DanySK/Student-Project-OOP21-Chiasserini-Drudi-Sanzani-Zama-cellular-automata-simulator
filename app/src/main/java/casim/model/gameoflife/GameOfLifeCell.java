package casim.model.gameoflife;

import casim.model.abstraction.cell.AbstractCell;

/**
 * Cell of Game Of Life.
 */
public class GameOfLifeCell extends AbstractCell<GameOfLifeState> {

     /**
     * Construct a new {@link GameOfLifeCell}.
     * 
     * @param state the current state of the {@link Cell}.
     */
    public GameOfLifeCell(final GameOfLifeState state) {
        super(state);
    }

}
