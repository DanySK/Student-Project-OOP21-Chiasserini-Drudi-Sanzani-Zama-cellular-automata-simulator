package casim.model.codi;

import java.util.List;
import java.util.function.BiFunction;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.rule.AbstractUpdateRule;
import casim.utils.coordinate.Coordinates3D;
import casim.utils.grid.Grid;

/**
 * Implementation of the {@link casim.model.abstraction.rule.UpdateRule} for {@link CoDi} automaton.
 */
public class UpdateRule extends AbstractUpdateRule<Coordinates3D<Integer>, CoDiCell> {

    /**
     * Constructor of {@link UpdateRule}.
     * 
     * @param neighborsFunction it takes as input a pair {@link Coordinates3D}+{@link CoDiCell} and the {@link Grid} of the 
     *     automaton and return a list of pair {@link Coordinates3D}+{@link CoDiCell} of all the neighbors of the cell taken
     *     as input.
     */
    public UpdateRule(
            final BiFunction<Pair<Coordinates3D<Integer>, CoDiCell>, Grid<Coordinates3D<Integer>, CoDiCell>, List<Pair<Coordinates3D<Integer>, CoDiCell>>> neighborsFunction) {
        super(neighborsFunction);
    }

    @Override
    protected CoDiCell nextCell(final Pair<Coordinates3D<Integer>, CoDiCell> cellPair,
            final List<Pair<Coordinates3D<Integer>, CoDiCell>> neighborsPairs) {
        // TODO Auto-generated method stub
        return null;
    }

}
