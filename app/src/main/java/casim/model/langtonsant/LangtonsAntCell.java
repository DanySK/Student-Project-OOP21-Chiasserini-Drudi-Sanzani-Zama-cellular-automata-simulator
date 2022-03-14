package casim.model.langtonsant;

import java.util.Optional;
import casim.model.abstraction.cell.AbstractCell;

public class LangtonsAntCell extends AbstractCell<CellState>{

    private Optional<Ant> ant = Optional.empty();

    public LangtonsAntCell(CellState state) {
        super(state);
    }
    
    public boolean hasAnt() {
        return this.ant.isPresent();
    }

    public Ant getAnt() {
        return this.ant.get();
    }
}
