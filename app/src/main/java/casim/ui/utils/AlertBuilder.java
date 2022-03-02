package casim.ui.utils;

import java.util.Objects;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.stage.Window;

/**
 * Implementation for a builder of {@link Alert}.
 */
public class AlertBuilder {

    private AlertType type;
    private String title;
    private String message;
    private Window owner;

    /**
     * Set the alert type.
     * 
     * @param type the type of the alert.
     * @return {@link AlertBuilder}.
     */
    public AlertBuilder setType(final AlertType type) {
        Objects.requireNonNull(type);
        this.type = type;
        return this;
    }

    /**
     * Set the alert title.
     * 
     * @param title
     * @return {@link AlertBuilder}. 
     */
    public AlertBuilder setTitle(final String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }

    /**
     * Set the alert message.
     * 
     * @param message
     * @return {@link AlertBuilder}.
     */
    public AlertBuilder setMessage(final String message) {
        Objects.requireNonNull(message);
        this.message = message;
        return this;
    }

    /**
     * Set the alert owner.
     * 
     * @param owner
     * @return {@link AlertBuilder}.
     */
    public AlertBuilder setOwner(final Window owner) {
        Objects.requireNonNull(owner);
        this.owner = owner;
        return this;
    }

    /**
     * Set the default values for an info alert.
     *  - Type: {@link AlertType.INFORMATION}.
     *  - Title: info
     * 
     * @return {@link AlertBuilder}.
     */
    public AlertBuilder setDefaultInfo() {
        return this.setType(AlertType.INFORMATION)
            .setTitle("Info");
    }

    /**
     * Set the default values for an info alert.
     *  - Type: {@link AlertType.ERROR}.
     *  - Title: error
     * 
     * @return {@link AlertBuilder}.
     */
    public AlertBuilder setDefaultError() {
        return this.setType(AlertType.ERROR)
            .setTitle("Error");
    }

    /**
     * 
     * @return the built {@link Alert}.
     */
    public Alert build() {
        final var alert = new Alert(this.type);
        alert.setTitle(this.title);
        alert.setHeaderText(this.title);
        alert.setContentText(this.message);
        alert.initOwner(this.owner);
        alert.getDialogPane().setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        return alert;
    }
}
