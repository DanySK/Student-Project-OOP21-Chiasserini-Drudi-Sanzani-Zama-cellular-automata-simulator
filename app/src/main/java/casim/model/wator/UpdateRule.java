package casim.model.wator;

import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.rule.AbstractUpdateRule;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.grid.Grid;

public class UpdateRule extends AbstractUpdateRule<Coordinates2D<Integer>, WatorCell>{

    private static final String UNKNOW_STATE = "Cell has unknown state.";
    private static final int NEWBORN_HEALTH = 1;
    private static final int DEAD_HEALTH = 0;

    public UpdateRule(
            BiFunction<Pair<Coordinates2D<Integer>, WatorCell>, Grid<Coordinates2D<Integer>, WatorCell>, List<Pair<Coordinates2D<Integer>, WatorCell>>> neighborsFunction) {
        super(neighborsFunction);
    }

    @Override
    protected WatorCell nextCell(Pair<Coordinates2D<Integer>, WatorCell> cellPair,
            List<Pair<Coordinates2D<Integer>, WatorCell>> neighborsPairs) {
        final var cell = cellPair.getRight();
        switch (cellPair.getRight().getState()) {
            case PREY:
                if (this.countState(neighborsPairs, CellState.DEAD) > 0) {
                    //move and reproduce if can
                    final var deadNeighbors = this.getFilteredList(neighborsPairs, CellState.DEAD);
                    // create newborn if at max health
                    WatorCell newCell = new WatorCell(CellState.DEAD, DEAD_HEALTH, cell.getMaxHealth());
                    if (cell.getHealth() == cell.getMaxHealth()) {
                        newCell = new WatorCell(CellState.PREY, NEWBORN_HEALTH, cell.getMaxHealth());
                        cell.setHealth(NEWBORN_HEALTH);
                    }
                    // move current prey to new position and heal by 1
                    var toChange = this.getCellToChange(deadNeighbors);
                    this.changeCell(toChange, CellState.PREY, cell.getHealth(), cell.getMaxHealth(), WatorCell::heal);
                    return newCell;
                } else {
                    // don't move
                    cell.heal();
                    return cell;
                }
            case PREDATOR:
                if (cell.isDead()) {
                    return new WatorCell(CellState.DEAD, DEAD_HEALTH, cell.getMaxHealth());
                } else if (this.countState(neighborsPairs, CellState.PREY) > 0) {
                    // move and eat
                    final var preyNeighbors = this.getFilteredList(neighborsPairs, CellState.PREY);
                    var toChange = this.getCellToChange(preyNeighbors);
                    this.changeCell(toChange, CellState.PREDATOR, cell.getHealth(), cell.getMaxHealth(), WatorCell::heal);
                    return new WatorCell(CellState.DEAD, DEAD_HEALTH, cell.getMaxHealth());
                } else if (this.countState(neighborsPairs, CellState.DEAD) > 0) {
                    // move and lose health
                    final var deadNeighbors = this.getFilteredList(neighborsPairs, CellState.DEAD);
                    var toChange = this.getCellToChange(deadNeighbors);
                    this.changeCell(toChange, CellState.PREDATOR, cell.getHealth(), cell.getMaxHealth(), WatorCell::starve);
                    return new WatorCell(CellState.DEAD, DEAD_HEALTH, cell.getMaxHealth());
                } else {
                    // don't move and lose health
                    cell.starve();
                    return cell;
                }
            case DEAD:
                return cell;

            default:
                throw new IllegalStateException(UNKNOW_STATE);
        }
    }
    
    private void changeCell(WatorCell toChange, final CellState state, final int health, final int maxHealth,
            Consumer<WatorCell> consumer) {
        toChange = new WatorCell(state, health, maxHealth);
        consumer.accept(toChange);
    }

    private WatorCell getCellToChange(final List<WatorCell> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    private long countState(final List<Pair<Coordinates2D<Integer>, WatorCell>> list, final CellState state) {
        return list.stream()
            .filter(x -> x.getValue().getState().equals(state))
            .count();
    }

    private List<WatorCell> getFilteredList(List<Pair<Coordinates2D<Integer>, WatorCell>> list, final CellState state) {
        return list.stream()
            .map(x -> x.getValue())
            .filter(x -> x.getState().equals(state))
            .collect(Collectors.toList());
    }

}
