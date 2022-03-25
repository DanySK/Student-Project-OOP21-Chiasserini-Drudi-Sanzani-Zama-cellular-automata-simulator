package casim.model.langtonsant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.utils.PlayableAutomaton;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid2D;

@PlayableAutomaton(AutomatonName = "Langton's Ant")
public class LangtonsAnt extends AbstractAutomaton<CellState, LangtonsAntCell>{

    private final List<Ant> ants = new ArrayList<>();
    private final Grid2D<LangtonsAntCell> state;

    public LangtonsAnt(final Grid2D<CellState> state) {
        this.state = state.map(x -> new LangtonsAntCell(x));
    }

    public LangtonsAnt(final Grid2D<CellState> state, final List<Ant> ants) {
        this(state);
        this.ants.addAll(ants);
    }

    public LangtonsAnt(final Grid2D<CellState> state, final int antNumber) {
        this(state);
        final var randAntList = IntStream.range(0, antNumber)
                .mapToObj(x -> {
                    final var rand = new Random();
                    final var position = CoordinatesUtil.random(state.getHeight(), state.getWidth());
                    final var direction = Direction.values()[rand.nextInt(Direction.values().length)];
                    return new Ant(direction, position);
                })
                .collect(Collectors.toList());
        this.ants.addAll(randAntList);
    }

    @Override
    public boolean hasNext() {
        return !this.ants.isEmpty();
    }

    @Override
    protected Grid2D<LangtonsAntCell> doStep() {
        this.ants.stream()
            .forEach(x -> this.antStep(x));
        this.removeAnts();
        return this.state;
    }

    private void antStep(final Ant ant) {
        ant.turn(this.state.get(ant.getPosition()).getState());
        this.state.set(ant.getPosition(), new LangtonsAntCell(
                (this.state.get(ant.getPosition()).getState() == CellState.OFF) ? CellState.ON : CellState.OFF));
        ant.move();
    }

    private void removeAnts() {
        // final List<Ant> toBeRemoved = this.ants.stream()
            // .filter(x -> !this.state.isCoordValid(x.getPosition()))
            // .collect(Collectors.toList());
        // this.ants.removeAll(toBeRemoved);
        this.ants.removeIf(x -> !this.state.isCoordValid(x.getPosition()));
        // toBeRemoved.stream()
            // .forEach(x -> this.ants.remove(x));
    }

    /**
     * Adds an {@link Ant} at a specified position with a specified direction.
     * 
     * @param direction the {@link Direction} of the {@link Ant} to be added.
     * @param position the {@link Coordinates2D} representing the position
     * of the and to be added.
     * @return True if the {@link Coordinates2D} given as argument are valid,
     * False otherwise.
     */
    public boolean addAnt(final Direction direction, final Coordinates2D<Integer> position) {
        if (this.state.isCoordValid(position)) {
            this.ants.add(new Ant(direction, position));
            return true;
        }
        return false;
    }

    /**
     * Adds an {@link Ant} at a random position with a random {@Link Direction}.
     * @return the {@link Coordinates2D} representig the position of the new {@link Ant}.
     */
    public Coordinates2D<Integer> addAnt() {
        final Random rand = new Random();
        final var position = CoordinatesUtil.random(state.getWidth(), state.getHeight());
        final var direction = Direction.values()[rand.nextInt(Direction.values().length)];
        this.addAnt(direction, position);
        return position;
    }

    @Override
    public Grid2D<LangtonsAntCell> getGrid() {
        return this.state;
        // TODO return unmodifiable copy
    }
    
}
