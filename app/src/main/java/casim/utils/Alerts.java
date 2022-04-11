package casim.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Utility class for {@link Alert}.
 */
public final class Alerts {

    private Alerts() {
    }

    /**
     * Build a new {@link Alert}.
     * 
     * @param type the type of the alert.
     * @param content the content to show in the alert.
     * @return the alert.
     */
    public static Alert of(final AlertType type, final String content) {
        final Alert alert = new Alert(type);
        alert.setAlertType(type);
        alert.setContentText(content);
        return alert;
    }

    /**
     * Build and show a new {@link Alert}.
     * 
     * @param type the type of the alert.
     * @param content the content to show in the alert.
     */
    public static void ofShow(final AlertType type, final String content) {
        final var alert = Alerts.of(type, content);
        alert.show();
    }

    /**
     * Build a new {@link Alert}, show it and wait for the response. 
     * 
     * @param type the type of the alert.
     * @param content the content to show in the alert.
     */
    public static void ofShowAndWait(final AlertType type, final String content) {
        final var alert = Alerts.of(type, content);
        alert.showAndWait();
    }
}
