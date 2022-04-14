package casim.ui.components.menu.automaton.config;

import java.util.Random;

import casim.model.Automata;
import casim.model.langtonsant.LangtonsAnt;
import casim.model.langtonsant.LangtonsAntConfig;
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
     * @param automata the automata to simulate.
     */
    public AutomatonWrapConfigController(final PageContainer container, final Automata automata) {
        super(container, automata);
        this.checkBox = new CheckBox("Wrapped Grid");
    }

    /**
     * Returns true if the wrapped configuration is selected, false otherwise.
     * 
     * @return true if the automaton uses a wrapped grid, false otherwise.
     */
    protected boolean isWrapped() {
        return this.checkBox.isSelected();
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.addExtension(checkBox);
    }

    @Override
    protected BaseConfig getConfig() {
        final var isAutomatic = this.isAutomatic();
        final var isWrapped = this.isWrapped();
        final int size = Integer.parseInt(this.getSizeText());

        switch (this.getAutomata()) {
            case BRYANS_BRAIN:
            case GAME_OF_LIFE:
                return (BaseConfig) new WrappingConfig(size, size, isAutomatic, isWrapped);
            case LANGTONS_ANT:
                final var rng = new Random();
                final var ants = LangtonsAnt.MIN_ANTS + rng.nextInt(LangtonsAnt.MAX_ANTS - LangtonsAnt.MIN_ANTS + 1);
                return (BaseConfig) new LangtonsAntConfig(size, size, isAutomatic, isWrapped, ants);
            default:
                throw new UnsupportedOperationException(WRONG_MENU);
        }
    }
}
