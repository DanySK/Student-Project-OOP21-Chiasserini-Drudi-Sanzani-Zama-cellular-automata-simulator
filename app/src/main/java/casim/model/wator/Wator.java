package casim.model.wator;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.utils.PlayableAutomaton;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid2D;
import casim.utils.range.Ranges;

/**
 * Predators and Preys automaton.
 */
@PlayableAutomaton(AutomatonName = "Predators and Preys")
public class Wator extends AbstractAutomaton<CellState, WatorCell> {

    private static final int MAX_HEALTH = 10;
    private static final int INIT_HEALTH = 5;
    private static final int DEAD_HEALTH = 0;

    private final Grid2D<WatorCell> state;

    /**
     * Builds a new {@link Wator} automaton with initial state from input.
     * 
     * @param state {@link Grid2D} of {@link CellState} representing the
     * initial state of the automaton.
     */
    public Wator(final Grid2D<CellState> state) {
        this.state = state.map(x -> new WatorCell(x, INIT_HEALTH, MAX_HEALTH));
    }

    @Override
    public boolean hasNext() {
        // return this.state.stream().anyMatch(x -> !x.getState().equals(CellState.DEAD));
        return true;
    }

    @Override
    protected Grid2D<WatorCell> doStep() {
        for (final var x : Ranges.of(0, this.state.getHeight())) {
            for (final var y : Ranges.of(0, this.state.getWidth())) {
                final var coord = CoordinatesUtil.of(x, y);
                final var currCell = this.state.get(coord);
                final var neighborsList = CoordinatesUtil.get2DNeighbors(coord).stream()
                        .filter(c -> this.state.isCoordValid(c))
                        .map(c -> this.state.get(c))
                        .collect(Collectors.toList());
                final var preyNeighbors = neighborsList.stream()
                        .filter(w -> w.getState().equals(CellState.PREY))
                        .collect(Collectors.toList());
                final var deadNeighbors = neighborsList.stream()
                        .filter(w -> w.getState().equals(CellState.DEAD))
                        .collect(Collectors.toList());
                switch (currCell.getState()) {
                    case PREY:
                        if (deadNeighbors.size() > 0) {
                            this.cellStep(currCell, deadNeighbors, WatorCell::heal);
                        } else {
                            currCell.heal();
                            currCell.move();
                        }
                        break;
                    case PREDATOR:
                        if (preyNeighbors.size() > 0) {
                            this.cellStep(currCell, preyNeighbors, WatorCell::heal);
                        } else if (deadNeighbors.size() > 0) {
                            this.cellStep(currCell, deadNeighbors, WatorCell::starve);
                        } else {
                            currCell.starve();
                            this.applyDeath(currCell);
                            currCell.move();
                        }
                        break;
                    default:
                        break;

                }
            }
        }
        this.state.stream().forEach(WatorCell::resetMovement);
        return this.state;
    }

    @Override
    public Grid2D<WatorCell> getGrid() {
        return this.state;
    }

    private void cellStep(final WatorCell currentCell, final List<WatorCell> neighbors, final Consumer<WatorCell> movementAction) {
        if (this.applyDeath(currentCell)) {
            return;
        } else if (currentCell.hasMoved()) {
            return;
        }
        final var rand = new Random();
        final var toChange = neighbors.get(rand.nextInt(neighbors.size()));
        toChange.setState(currentCell.getState());
        toChange.setHealth(currentCell.getHealth());
        movementAction.accept(toChange);
        toChange.move();
        final var spawn = toChange.reproduce();
        currentCell.setState(spawn.getState());
        currentCell.setHealth(spawn.getHealth());
        currentCell.move();
    }

    private boolean applyDeath(final WatorCell currentCell) {
        if (currentCell.isDead()) {
            currentCell.setState(CellState.DEAD);
            currentCell.setHealth(DEAD_HEALTH);
            return true;
        } else {
            return false;
        }
    }

}
