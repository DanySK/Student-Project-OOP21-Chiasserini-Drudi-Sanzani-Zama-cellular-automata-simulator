package casim.utils.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import casim.utils.coordinate.Coordinates;

/**
 * Utility class for {@link Grid}.
 */
public final class Grids<T> {
	
    public Grids() {
    		
    }

    /**
     * Return an unmodifiable copy of the source {@link Grid}.
     * 
     * @param <T> the type of the {@link Grid}.
     * @param source the source {@link Grid}.
     * @return a {@link Grid}, an unmodifiable copy of source.
     */
    public static <T> Grid<T> getUnmodifiableCopy(final Grid<T> source) {
        return null; //TODO
    }

}
