package casim.ui.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import casim.model.bryansbrain.BryansBrainCellState;
import casim.model.codi.cell.attributes.CoDiCellState;
import casim.model.gameoflife.GameOfLifeState;
import casim.model.langtonsant.LangtonsAntCellState;
import casim.model.rule110.Rule110CellState;
import casim.model.wator.WatorCellState;
import casim.utils.Colors;
import javafx.scene.paint.Color;

/**
 * Factory for {@link StateColorMapper} configuration.
 */
public final class StateColorMapperFactory {

    private StateColorMapperFactory() {
    }

    /**
     * Return the {@link StateColorMapper} for CoDi automaton.
     * 
     * @return the {@link StateColorMapper} for CoDi automaton.
     */
    public static StateColorMapper<CoDiCellState> getCoDiStateColorMapper() {
        final var colorList = List.of(Colors.BLACK, Colors.AMETHYST, Colors.FUSCIA,
            Colors.CARROT, Colors.ARCTIC, Colors.PARAKEET);
        return getColorMapperFromLists(Arrays.asList(CoDiCellState.values()), colorList);
    }

    /**
     * Returns the {@link StateColorMapper} for Langton's Ant automaton.
     * 
     * @return the {@link StateColorMapper} for Langton's Ant automaton.
     */
    public static StateColorMapper<LangtonsAntCellState> getLangtonsAntStateColorMapper() {
        return getColorMapperFromLists(
            Arrays.asList(LangtonsAntCellState.values()), 
            List.of(Colors.WHITE, Colors.BLACK));
    }

    /**
     * Returns the {@link StateColorMapper} for Wator automaton.
     * 
     * @return the {@link StateColorMapper} for Wator automaton.
     */
    public static StateColorMapper<WatorCellState> getWatorStateColorMapper() {
        final var colorList = List.of(Colors.RED, Colors.GREEN, Colors.BLACK);
        return getColorMapperFromLists(Arrays.asList(WatorCellState.values()), colorList);
    }

    /**
     * Returns the {@link StateColorMapper} for Game of life automaton.
     * 
     * @return the {@link StateColorMapper} for Game of life automaton.
     */
    public static StateColorMapper<GameOfLifeState> getGameOfLifeStateColorMapper() {
        final var colorList = List.of(Colors.WHITE, Colors.BLACK);
        return getColorMapperFromLists(Arrays.asList(GameOfLifeState.values()), colorList);
    }

    /**
     * Returns the {@link StateColorMapper} for Bryan's Brain automaton.
     * 
     * @return the {@link StateColorMapper} for Bryan's Brain automaton.
     */
    public static StateColorMapper<BryansBrainCellState> getBryansBrainStateColorMapper() {
        return getColorMapperFromLists(
            Arrays.asList(BryansBrainCellState.values()),
            List.of(Colors.WHITE, Colors.LIGHT_BLUE, Colors.BLACK));
    }

    /**
     * Returns the {@link StateColorMapper} for Rule 110 automaton.
     * 
     * @return the {@link StateColorMapper} for Rule 110 automaton.
     */
    public static StateColorMapper<Rule110CellState> getRule110StateColorMapper() {
        return getColorMapperFromLists(
            Arrays.asList(Rule110CellState.values()),
            List.of(Colors.WHITE, Colors.BLACK));
    }

    private static <T> StateColorMapper<T> getColorMapperFromLists(final List<T> states, final List<Color> colors) {
        final var stateToColor = zipToMap(states, colors);
        return (state) -> stateToColor.get(state);
    }

    private static <T> Map<T, Color> zipToMap(final List<T> values, final List<Color> colors) {
        if (values.size() != colors.size()) {
            throw new IllegalArgumentException();
        }
        final Iterator<T> valuesIterator = values.iterator();
        final Iterator<Color> colorsIterator = colors.iterator();
        return IntStream.range(0, values.size()).boxed()
                .collect(Collectors.toMap(k -> valuesIterator.next(), v -> colorsIterator.next()));
    }
}
