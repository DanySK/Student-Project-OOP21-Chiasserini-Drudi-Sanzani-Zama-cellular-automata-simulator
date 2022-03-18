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

/**
 * Implementation of {@link AbstractUpdateRule} for Predators and Preys Automaton.
 */
public class UpdateRule extends AbstractUpdateRule<Coordinates2D<Integer>, WatorCell> {

    private static final String UNKNOW_STATE = "Cell has unknown state.";
    private static final int DEAD_HEALTH = 0;

    /**
     * Constructs a new {@link UpdateRule}.
     * 
     * @param neighborsFunction {@link BiFunction} used to obtain the
     * neighbors of a cell.
     */
    public UpdateRule(
            final BiFunction<Pair<Coordinates2D<Integer>, WatorCell>,
                Grid<Coordinates2D<Integer>, WatorCell>, List<Pair<Coordinates2D<Integer>, WatorCell>>> neighborsFunction) {
        super(neighborsFunction);
    }

    @Override
    protected WatorCell nextCell(final Pair<Coordinates2D<Integer>, WatorCell> cellPair,
            final List<Pair<Coordinates2D<Integer>, WatorCell>> neighborsPairs) {
        final var cell = cellPair.getRight();
        final var newCell = cell.reproduce();
        switch (cellPair.getRight().getState()) {
            case PREY:
                if (this.countState(neighborsPairs, CellState.DEAD) > 0) {
                    //move and reproduce if can
                    final var deadNeighbors = this.getFilteredList(neighborsPairs, CellState.DEAD);
                    // move current prey to new position and heal by 1
                    var toChange = this.getCellToChange(deadNeighbors);
                    this.move(toChange, CellState.PREY, cell.getHealth(), cell.getMaxHealth(), WatorCell::heal);
                    return newCell;
                } else {
                    // don't move
                    cell.heal();
                    return cell;
                }
            case PREDATOR:
                if (this.countState(neighborsPairs, CellState.PREY) > 0) {
                    // move and eat
                    final var preyNeighbors = this.getFilteredList(neighborsPairs, CellState.PREY);
                    var toChange = this.getCellToChange(preyNeighbors);
                    this.move(toChange, CellState.PREDATOR, cell.getHealth(), cell.getMaxHealth(), WatorCell::heal);
                    return newCell;
                } else if (this.countState(neighborsPairs, CellState.DEAD) > 0) {
                    // move and lose health
                    final var deadNeighbors = this.getFilteredList(neighborsPairs, CellState.DEAD);
                    var toChange = this.getCellToChange(deadNeighbors);
                    this.move(toChange, CellState.PREDATOR, cell.getHealth(), cell.getMaxHealth(), WatorCell::starve);
                    return newCell;
                } else {
                    // don't move and lose health
                    cell.starve();
                    if (cell.isDead()) {
                        return new WatorCell(CellState.DEAD, DEAD_HEALTH, cell.getMaxHealth());
                    }
                    return cell;
                }
            case DEAD:
                return cell;

            default:
                throw new IllegalStateException(UNKNOW_STATE);
        }
    }

    private void move(WatorCell toChange, final CellState state, final int health, final int maxHealth,
            Consumer<WatorCell> consumer) {
        var newCell = new WatorCell(state, health, maxHealth);
        consumer.accept(toChange);
        if (newCell.isDead()) {
            newCell = new WatorCell(CellState.DEAD, DEAD_HEALTH, maxHealth);
        }
        toChange = newCell;
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
