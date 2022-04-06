package casim.ui.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import casim.model.codi.cell.attributes.CoDiCellState;
import casim.model.langtonsant.LangtonsAntCellState;
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
        return new StateColorMapper<CoDiCellState>() {
            private final List<CoDiCellState> stateList = Arrays.asList(CoDiCellState.values());
            private final List<Color> colorList = List.of(Colors.BLACK, Colors.AMETHYST, Colors.FUSCIA,
                    Colors.CARROT, Colors.ARCTIC, Colors.PARAKEET);
            private final Map<CoDiCellState, Color> stateToColor = zipToMap(stateList, colorList);

            @Override
            public Color toColor(final CoDiCellState state) {
                throwsIfNotPresent(stateList, state);
                return stateToColor.get(state);
            }
        };
    }

    /**
     * Returns the {@link StateColorMapper} for Langton's Ant automaton.
     * 
     * @return the {@link StateColorMapper} for Langton's Ant automaton.
     */
    public static StateColorMapper<LangtonsAntCellState> getLangtonsAntStateColorMapper() {
        return new StateColorMapper<LangtonsAntCellState>() {
            private final List<LangtonsAntCellState> stateList = Arrays.asList(LangtonsAntCellState.values());
            private final List<Color> colorList = List.of(Colors.WHITE, Colors.BLACK);

            @Override
            public Color toColor(final LangtonsAntCellState state) {
                throwsIfNotPresent(stateList, state);
                return colorList.get(stateList.indexOf(state));
            }
        };
    }

    private static <T> void throwsIfNotPresent(final List<T> list, final T value) {
        if (!list.contains(value)) {
            throw new IllegalArgumentException("Invalid State");
        }
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
