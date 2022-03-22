package casim.model.gameoflife;

import casim.model.abstraction.cell.AbstractCell;

/**
 * Cell of Game Of Life.
 * 
 * @param <GameOfLifeState> the enumeration which contains the finite states of the {@link casim.model.gameoflife.GameOfLife}'s {@link Cell}.
 */
public class GameOfLifeCell extends AbstractCell<GameOfLifeState> {

     /**
     * Construct a new {@link GameOfLifeCell}.
     * 
     * @param state the current state of the {@link Cell}.
     */
    public GameOfLifeCell(GameOfLifeState state) {
        super(state);
    }
    
}