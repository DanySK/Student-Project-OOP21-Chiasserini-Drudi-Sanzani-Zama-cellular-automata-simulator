package casim.ui.utils;

import javafx.scene.paint.Color;

public interface StateColorMapper<T> {
    Color toColor(T state);
}
