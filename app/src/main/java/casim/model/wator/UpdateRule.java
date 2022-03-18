package casim.model.wator;

import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
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
                if (neighborsPairs.stream().filter(x -> x.getRight().getState().equals(CellState.DEAD)).count() > 0) {
                    //move and reproduce if can
                    final var deadNeighbors = neighborsPairs.stream()
                        .filter(x -> x.getRight().getState().equals(CellState.DEAD))
                        .collect(Collectors.toList());
                    WatorCell newCell = null;
                    // create newborn if at max health
                    if (cell.getHealth() == cell.getMaxHealth()) {
                        newCell = new WatorCell(CellState.PREY, NEWBORN_HEALTH, cell.getMaxHealth());
                        cell.setHealth(NEWBORN_HEALTH);
                    } else {
                        newCell = new WatorCell(CellState.DEAD, DEAD_HEALTH, cell.getMaxHealth());
                    }
                    // move current prey to new position and heal by 1
                    final var rand = new Random();
                    deadNeighbors.get(rand.nextInt(deadNeighbors.size())).setValue(new WatorCell(CellState.PREY, cell.getHealth() + 1, cell.getHealth()));
                    return newCell;
                } else {
                    // don't move
                    cell.heal();
                    return cell;
                }
            case PREDATOR:
                if (cell.isDead()) {
                    return new WatorCell(CellState.DEAD, DEAD_HEALTH, cell.getMaxHealth());
                } else if (neighborsPairs.stream().filter(x -> x.getRight().getState().equals(CellState.PREY)).count() > 0) {
                    // move and eat
                    final var preyNeighbors = neighborsPairs.stream()
                        .filter(x -> x.getRight().getState().equals(CellState.PREY))
                        .collect(Collectors.toList());
                    final var rand = new Random();
                    final var randIndex = rand.nextInt(preyNeighbors.size());
                    preyNeighbors.get(randIndex).setValue(new WatorCell(CellState.PREDATOR, cell.getHealth(), cell.getMaxHealth()));
                    preyNeighbors.get(randIndex).getValue().heal();
                    return new WatorCell(CellState.DEAD, DEAD_HEALTH, cell.getMaxHealth());
                } else if (neighborsPairs.stream().filter(x -> x.getRight().getState().equals(CellState.DEAD)).count() > 0) {
                    // move and lose health
                    final var deadNeighbors = neighborsPairs.stream()
                        .filter(x -> x.getRight().getState().equals(CellState.DEAD))
                        .collect(Collectors.toList());
                    final var rand = new Random();
                    final var randIndex = rand.nextInt(deadNeighbors.size());
                    deadNeighbors.get(randIndex).setValue(new WatorCell(CellState.PREDATOR, cell.getHealth(), cell.getMaxHealth()));
                    deadNeighbors.get(randIndex).getValue().starve();
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
    
}
