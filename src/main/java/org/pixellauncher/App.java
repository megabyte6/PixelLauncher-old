package org.pixellauncher;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pixellauncher.setting.Settings;
import org.pixellauncher.util.OS;

public class App extends Application {

    public static final Logger LOGGER = LogManager.getLogger(App.class);

    @Getter
    private static Stage stage;

    @Getter
    @Setter
    private static Settings settings;

    public static void main(String[] args) {
        App.LOGGER.info("Starting " + Constants.LAUNCHER_NAME.replaceAll("\\s+", ""));
        if (!OS.createStorageDirectory()) {
            App.LOGGER.fatal("Failed to create the app config and data directory at '"
                    + OS.getStoragePath().toAbsolutePath() + "'");
            App.LOGGER.info("Stopping " + Constants.LAUNCHER_NAME.replaceAll("\\s+", ""));
            return;
        }
        settings = Settings.loadElseDefault(Constants.CONFIG_PATH);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        App.stage = primaryStage;

        App.LOGGER.debug("Loading Main scene");
        final Parent root = ResourceLoader.loadFXML("Main").load();
        final Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        App.LOGGER.debug("Loading theme");
        MFXThemeManager.addOn(scene, Themes.DEFAULT, getSettings().getTheme());

        if (getSettings().isSaveWindowLocation()) {
            App.LOGGER.debug("Restoring window starting location");
            primaryStage.setX(getSettings().getLauncherPosition().getX());
            primaryStage.setY(getSettings().getLauncherPosition().getY());
        }
        if (getSettings().isSaveWindowSize()) {
            App.LOGGER.debug("Restoring window size");
            primaryStage.setWidth(getSettings().getLauncherSize().getWidth());
            primaryStage.setHeight(getSettings().getLauncherSize().getHeight());
        }

        App.LOGGER.debug("Loading icon");
        primaryStage.getIcons().add(new Image(ResourceLoader.loadStream("Pixel Launcher.png")));
        primaryStage.setTitle(Constants.LAUNCHER_NAME);
        App.LOGGER.info("Showing window");
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();

        writeSettings();

        App.LOGGER.info("Stopping " + Constants.LAUNCHER_NAME.replaceAll("\\s+", ""));
    }

    public static void writeSettings() {
        // Update settings with new window position and size.
        getSettings().setLauncherPosition(new Point2D(App.getStage().getX(), App.getStage().getY()));
        getSettings().setLauncherSize(new Dimension2D(App.getStage().getWidth(), App.getStage().getHeight()));

        try {
            App.LOGGER.info("Saving settings");
            settings.save(Constants.CONFIG_PATH);
        } catch (Exception e) {
            App.LOGGER.warn("Failed to write settings :(", e);
        }
    }

}
