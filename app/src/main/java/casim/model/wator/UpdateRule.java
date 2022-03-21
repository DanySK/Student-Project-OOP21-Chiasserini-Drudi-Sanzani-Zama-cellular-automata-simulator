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
        final var neighborsList = neighborsPairs.stream()
            .map(x -> x.getValue())
            .collect(Collectors.toList());
        switch (cell.getState()) {
            case PREY:
                if (this.countState(neighborsPairs, CellState.DEAD) > 0) {
                    //move and reproduce if can
                    // move current prey to new position and heal by 1
                    this.cellStep(neighborsList, CellState.DEAD, cell, WatorCell::heal);
                    return newCell;
                } else {
                    // don't move
                    cell.heal();
                    return cell;
                }
            case PREDATOR:
                if (this.countState(neighborsPairs, CellState.PREY) > 0) {
                    // move and eat
                    this.cellStep(neighborsList, CellState.PREY, cell, WatorCell::heal);
                    return newCell;
                } else if (this.countState(neighborsPairs, CellState.DEAD) > 0) {
                    // move and lose health
                    this.cellStep(neighborsList, CellState.DEAD, cell, WatorCell::starve);
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
            final Consumer<WatorCell> action) {
        var newCell = new WatorCell(state, health, maxHealth);
        if (newCell.isDead()) {
            newCell = new WatorCell(CellState.DEAD, DEAD_HEALTH, maxHealth);
        }
        toChange = newCell;
        action.accept(toChange);
    }

    private WatorCell getCellToChange(final List<WatorCell> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    private long countState(final List<Pair<Coordinates2D<Integer>, WatorCell>> list, final CellState state) {
        return list.stream()
            .filter(x -> x.getValue().getState().equals(state))
            .count();
    }

    private List<WatorCell> getFilteredList(final List<WatorCell> list, final CellState state) {
        return list.stream()
            .filter(x -> x.getState().equals(state))
            .collect(Collectors.toList());
    }

    private void cellStep(final List<WatorCell> neighborsList, final CellState neighborsFilter,
            final WatorCell currCell, final Consumer<WatorCell> action) {
        final var filteredNeighbors = this.getFilteredList(neighborsList, neighborsFilter);
        var toChange = this.getCellToChange(filteredNeighbors);
        this.move(toChange, currCell.getState(), currCell.getHealth(), currCell.getMaxHealth(), action);
    }

}
