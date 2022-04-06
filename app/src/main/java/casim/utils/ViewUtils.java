package casim.utils;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public final class ViewUtils {
    public static void fitToAnchorPane(final Node node) {
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }
}
