package casim.utils;

import casim.controller.automaton.AutomatonControllerImpl;
import casim.controller.automaton.CoDiControllerImpl;
import casim.controller.menu.MenuControllerImpl;
import casim.model.Automata;
import casim.model.bryansbrain.BryansBrainCellState;
import casim.model.codi.CoDiConfig;
import casim.model.gameoflife.GameOfLifeState;
import casim.model.langtonsant.LangtonsAntCellState;
import casim.model.langtonsant.LangtonsAntConfig;
import casim.model.rule110.Rule110CellState;
import casim.model.wator.WatorCellState;
import casim.ui.components.grid.CanvasGridBuilderImpl;
import casim.ui.components.grid.CanvasGridImpl;
import casim.ui.components.menu.automaton.AutomatonMenu;
import casim.ui.components.menu.automaton.config.AutomatonConfigController;
import casim.ui.components.menu.automaton.config.AutomatonWrapConfigController;
import casim.ui.components.page.PageContainer;
import casim.ui.utils.StateColorMapperFactory;
import casim.ui.utils.ViewEnum;
import casim.ui.view.AutomatonViewController;
import casim.ui.view.CoDiViewController;
import casim.ui.view.ConcurrentAutomatonViewController;
import casim.utils.automaton.AutomatonFactoryImpl;
import casim.utils.automaton.config.BaseConfig;
import casim.utils.automaton.config.WrappingConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public final class AppManager {
    private static final String UNKNOWN_AUTOMATON = "Unknown automaton.";
    private static final AutomatonFactoryImpl AUTOMATON_FACTORY = new AutomatonFactoryImpl();

    public static void showMainMenu(final PageContainer container) {
        final var controller = new MenuControllerImpl();
        final var menu = new AutomatonMenu(container, controller);
        container.addPage(menu);
    }

    public static Result<Empty> showConfigMenu(final Automata automata, final PageContainer container) {
        final var loader = new FXMLLoader(AppManager.class.getResource(ViewEnum.AUTOMATON_CONFIG_MENU.getResourceName()));
        final var controller = getConfigController(automata, container);
        loader.setController(controller);
        final Result<VBox> view = Result.executeSupplier(() -> (VBox) loader.load());
        view.ifPresent(container::addPage);
        return view.map(x -> new Empty() {});
    }

    public static Result<Empty> showSimulation(final Automata automata, final PageContainer container, final BaseConfig config) {
        final var loader = new FXMLLoader(AppManager.class.getResource(ViewEnum.AUTOMATON_VIEW.getResourceName()));
        final var viewController = getAutomatonViewControllerFromAutomata(automata, container, config);
        loader.setController(viewController);
        final Result<VBox> view = Result.executeSupplier(() -> (VBox) loader.load());
        view.ifPresent(container::addPage);
        return view.map(x -> new Empty() {});
    }

    private static <T extends AutomatonViewController<?>> T getAutomatonViewControllerFromAutomata(final Automata automata, final PageContainer container, final BaseConfig config) {
        switch (automata) {
            case CODI:
                return (T) getCoDiViewController(container, (CoDiConfig) config);
            case RULE110:
                return (T) getRule110ViewController(container, config);
            case WATOR:
                return (T) getWatorViewController(container, config);
            case BRYANS_BRAIN:
                return (T) getBryansBrainViewController(container, (WrappingConfig) config);
            case GAME_OF_LIFE:
                return (T) getGameOfLifeViewController(container, (WrappingConfig) config);
            case LANGTONS_ANT:
                return (T) getLangtonsAntViewController(container, (LangtonsAntConfig) config);
            default:
                throw new IllegalArgumentException(UNKNOWN_AUTOMATON);
        }
    }

    private static AutomatonViewController<BryansBrainCellState> getBryansBrainViewController(final PageContainer container, final WrappingConfig config) {
        final var automaton = AUTOMATON_FACTORY.getBryansBrainRandom(config);
        final var controller = new AutomatonControllerImpl<>(automaton);
        final var grid = getGrid(config.getCols(), config.getRows());
        final var mapper = StateColorMapperFactory.getBryansBrainStateColorMapper();
        return config.isAutomatic()
            ? new ConcurrentAutomatonViewController<>(container, controller, grid, mapper)
            : new AutomatonViewController<>(container, controller, grid, mapper);
    }

    private static CoDiViewController getCoDiViewController(final PageContainer container, final CoDiConfig config) {
        final var automaton = AUTOMATON_FACTORY.getCoDi(config);
        final var controller = new CoDiControllerImpl(automaton);
        final var grid = getGrid(config.getCols(), config.getRows());
        final var mapper = StateColorMapperFactory.getCoDiStateColorMapper();
        // return config.isAutomatic() TODO:
        //     ? new ConcurrentAutomatonViewController<>(container, controller, grid, mapper)
        //     : new AutomatonViewController<>(container, controller, grid, mapper);
        return new CoDiViewController(container, controller, grid, mapper);
    }

    private static AutomatonViewController<WatorCellState> getWatorViewController(final PageContainer container, final BaseConfig config) {
        final var automaton = AUTOMATON_FACTORY.getWator(config);
        final var controller = new AutomatonControllerImpl<>(automaton);
        final var grid = getGrid(config.getCols(), config.getRows());
        final var mapper = StateColorMapperFactory.getWatorStateColorMapper();
        return config.isAutomatic()
            ? new ConcurrentAutomatonViewController<>(container, controller, grid, mapper)
            : new AutomatonViewController<>(container, controller, grid, mapper);
    }

    private static AutomatonViewController<LangtonsAntCellState> getLangtonsAntViewController(final PageContainer container, final LangtonsAntConfig config) {
        final var automaton = AUTOMATON_FACTORY.getLangtonsAnt(config);
        final var controller = new AutomatonControllerImpl<>(automaton);
        final var grid = getGrid(config.getCols(), config.getRows());
        final var mapper = StateColorMapperFactory.getLangtonsAntStateColorMapper();
        return config.isAutomatic()
            ? new ConcurrentAutomatonViewController<>(container, controller, grid, mapper)
            : new AutomatonViewController<>(container, controller, grid, mapper);
    }

    private static AutomatonViewController<Rule110CellState> getRule110ViewController(final PageContainer container, final BaseConfig config) {
        final var automaton = AUTOMATON_FACTORY.getRule110(config);
        final var controller = new AutomatonControllerImpl<>(automaton);
        final var grid = getGrid(config.getCols(), config.getRows());
        final var mapper = StateColorMapperFactory.getRule110StateColorMapper();
        return config.isAutomatic()
            ? new ConcurrentAutomatonViewController<>(container, controller, grid, mapper)
            : new AutomatonViewController<>(container, controller, grid, mapper);
    }

    private static AutomatonViewController<GameOfLifeState> getGameOfLifeViewController(final PageContainer container, final WrappingConfig config) {
        final var automaton = AUTOMATON_FACTORY.getGameOfLife(config);
        final var controller = new AutomatonControllerImpl<>(automaton);
        final var grid = getGrid(config.getCols(), config.getRows());
        final var mapper = StateColorMapperFactory.getGameOfLifeStateColorMapper();
        return config.isAutomatic()
            ? new ConcurrentAutomatonViewController<>(container, controller, grid, mapper)
            : new AutomatonViewController<>(container, controller, grid, mapper);
    }

    private static AutomatonConfigController getConfigController(final Automata automata, final PageContainer container) {
        switch (automata) {
            case CODI:
            case RULE110:
            case WATOR:
                return new AutomatonConfigController(container, automata);
            case BRYANS_BRAIN:
            case GAME_OF_LIFE:
                return new AutomatonWrapConfigController(container, automata);
            case LANGTONS_ANT:
                throw new UnsupportedOperationException();
            default:
                throw new IllegalArgumentException(UNKNOWN_AUTOMATON);
        }
    }

    private static CanvasGridImpl getGrid(final int cols, final int rows) {
        final var grid = new CanvasGridBuilderImpl().build(rows, cols);
        return (CanvasGridImpl) grid;
    }
}
