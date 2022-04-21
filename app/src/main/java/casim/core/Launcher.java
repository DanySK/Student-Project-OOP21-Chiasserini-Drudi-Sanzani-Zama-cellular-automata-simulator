package casim.core;

import java.awt.GraphicsEnvironment;

import casim.ui.components.page.PageContainer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Launcher of the JavaFX graphics.
 */
public class Launcher extends Application {
    private PageContainer root;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final var graphics = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        final var width = Math.max(800, graphics.getDisplayMode().getWidth() / 2);
        final var height = Math.max(700, graphics.getDisplayMode().getHeight() / 2);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);

        root = new PageContainer(primaryStage);
        final var appManager = new AppManagerImpl(root);
        appManager.showMainMenu();
        final var scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        root.close();
    }
}
