package casim.ui.utils;

import java.util.Objects;

import casim.utils.AbstractBuilder;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.stage.Window;

/**
 * Implementation for a builder of {@link Alert}.
 */
public class AlertBuilderImpl extends AbstractBuilder implements AlertBuilder {
    private static final String ALERT_TYPE_NULL = "Alert type cannot be null.";
    private static final String TITLE_NULL = "Title cannot be null.";
    private static final String MESSAGE_NULL = "Message cannot be null.";
    private static final String OWNER_NULL = "Owner cannot be null.";
    private static final String DEFAULT_INFO_TITLE = "Title";
    private static final String DEFAULT_ERROR_TITLE = "Error";

    private AlertType type;
    private String title;
    private String message;
    private Window owner;

    /**
     * Set the default values for an info alert.
     *  - Type: {@link AlertType.INFORMATION}.
     *  - Title: Info
     * 
     * @param message the message of the alert.
     * @param owner the owner of the alert.
     * @return {@link Alert}.
     */
    public Alert buildDefaultInfo(final String message, final Window owner) {
        return this.buildCustom(AlertType.INFORMATION, DEFAULT_INFO_TITLE, message, owner);
    }

    /**
     * Set the default values for an info alert.
     *  - Type: {@link AlertType.ERROR}.
     *  - Title: Error
     * 
     * @param message the message of the alert.
     * @param owner the owner of the alert.
     * @return {@link Alert}.
     */
    public Alert buildDefaultError(final String message, final Window owner) {
        return this.buildCustom(AlertType.ERROR, DEFAULT_ERROR_TITLE, message, owner);
    }

    /**
     * Sets the message and the owner of the {@link Alert}.
     * 
     * Throws if some of the parameters are not set.
     * 
     * @param type the type of the alert.
     * @param title the title of the alert.
     * @param message the message of the alert.
     * @param owner the owner of the alert.
     * @return the built {@link Alert}.
     */
    public Alert buildCustom(final AlertType type, final String title, final String message, final Window owner) {
        this.registerCall();
        this.type(type)
            .title(title)
            .owner(owner)
            .message(message);
        return this.getAlert();
    }

    private AlertBuilderImpl message(final String message) {
        this.registerCall();
        this.message = this.checkNonNullValue(message, MESSAGE_NULL);;
        return this;
    }
    
    private AlertBuilderImpl owner(final Window owner) {
        this.registerCall();
        this.owner = this.checkNonNullValue(owner, OWNER_NULL);
        return this;
    }

    private AlertBuilderImpl type(final AlertType type) {
        this.registerCall();
        this.type = this.checkNonNullValue(type, ALERT_TYPE_NULL);
        return this;
    }

    private AlertBuilderImpl title(final String title) {
        this.registerCall();
        this.title = this.checkNonNullValue(title, TITLE_NULL);
        return this;
    }

    private Alert getAlert() {
        final var alert = new Alert(this.type);
        alert.setTitle(this.title);
        alert.setHeaderText(this.title);
        alert.setContentText(this.message);
        alert.initOwner(this.owner);
        alert.getDialogPane().setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        return alert;
    }
}
