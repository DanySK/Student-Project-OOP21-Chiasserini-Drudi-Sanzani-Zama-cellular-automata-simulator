package casim.ui.utils;

import java.util.Arrays;
import java.util.List;

import casim.model.codi.cell.attributes.CoDiCellState;
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

            @Override
            public Color toColor(final CoDiCellState state) {
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
}
