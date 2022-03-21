package casim.model.gameoflife;
//TODO javadoc
public class UpdateRule extends AbstractUpdateRule<Coordinates2D<Integer>, GameOfLifeCell> {

    public UpdateRule(BiFunction<Pair<Coordinates2D<Integer>, GameOfLifeCell>, Grid<Coordinates2D<Integer>, GameOfLifeCell>, List<Pair<Coordinates2D<Integer>, GameOfLifeCell>>> neighborsFunction) {
        super(neighborsFunction);
    }

    @Override
    protected GameOfLifeCell nextCell(Pair<Coordinates2D<Integer>, GameOfLifeCell> cellPair,
        //TODO implement the rule of the automaton.
    }

    private int countAliveNeighbors(Iterable<Pair<Coordinates2D<Integer>, GameOfLifeCell>> neighborsPairs) {
        //TODO implement support method for count neighbors alive.
    }
}