package casim.ui.components.menu.automaton.config;

import casim.model.Automata;
import casim.ui.components.page.PageContainer;
import casim.utils.automaton.config.BaseConfig;
import casim.utils.automaton.config.WrappingConfig;
import javafx.scene.control.CheckBox;

/**
 * Controller class for the fxml AutomatonConfigMenu with also the wrap configuration.
 */
public class AutomatonWrapConfigController extends AutomatonConfigController {
    private final CheckBox checkBox;

    /**
     * Construct a new {@link AutomatonWrapConfigController}.
     * 
     * @param container the container of the menu.
     * @param controller the controller.
     */
    public AutomatonWrapConfigController(final PageContainer container, final Automata automata) {
        super(container, automata);
        this.checkBox = new CheckBox("Wrapped Grid");
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.addExtension(checkBox);
    }

    @Override
    protected BaseConfig getConfig() {
        final var isAutomatic = this.isAutomatic();
        final var isWrapped = this.checkBox.isSelected();
        final int size = Integer.parseInt(this.getSizeText());

        switch (this.getAutomata()) {
            case BRYANS_BRAIN:
            case GAME_OF_LIFE:
                return (BaseConfig) new WrappingConfig(size, size, isAutomatic, isWrapped);
            case LANGTONS_ANT:
            default:
                throw new UnsupportedOperationException(WRONG_MENU);
        }
    }
}
