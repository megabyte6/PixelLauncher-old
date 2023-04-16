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

    public static final Logger LOG = LogManager.getLogger(App.class);

    @Getter
    private static Stage stage;

    @Getter
    @Setter
    private static Settings settings;

    public static void main(String[] args) {
        App.LOG.info("Starting " + Constants.LAUNCHER_NAME.replace("\\s+", ""));
        if (!OS.createStorageDirectory()) {
            App.LOG.fatal("Failed to create the app config and data directory!");
            App.LOG.info("Stopping " + Constants.LAUNCHER_NAME.replace("\\s+", ""));
            return;
        }
        settings = Settings.loadElseDefault(Constants.CONFIG_PATH);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        App.stage = primaryStage;

        App.LOG.debug("Loading Main scene");
        final Parent root = ResourceLoader.loadFXML("Main").load();
        final Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        // Load the correct theme.
//        final String styleSheetPath = Theme.getStyleSheet(getSettings().getTheme()).toString();
//        scene.getStylesheets().setAll(styleSheetPath);
        App.LOG.debug("Loading theme");
        MFXThemeManager.addOn(scene, Themes.DEFAULT, getSettings().getTheme());

        // Set persistent window properties.
        if (getSettings().isSaveWindowSizeAndLocation()) {
            App.LOG.debug("Restoring window size and starting location");
            primaryStage.setX(getSettings().getLauncherPosition().getX());
            primaryStage.setY(getSettings().getLauncherPosition().getY());
            primaryStage.setWidth(getSettings().getLauncherSize().getWidth());
            primaryStage.setHeight(getSettings().getLauncherSize().getHeight());
        }

        App.LOG.debug("Loading icon");
        primaryStage.getIcons().add(new Image(ResourceLoader.loadStream("Pixel Launcher.png")));
        primaryStage.setTitle(Constants.LAUNCHER_NAME);
        App.LOG.info("Showing window");
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();

        writeSettings();

        App.LOG.info("Stopping" + Constants.LAUNCHER_NAME.replace("\\s+", ""));
    }

    public static void writeSettings() {
        // Update settings with new window position and size.
        getSettings().setLauncherPosition(new Point2D(App.getStage().getX(), App.getStage().getY()));
        getSettings().setLauncherSize(new Dimension2D(App.getStage().getWidth(), App.getStage().getHeight()));

        try {
            App.LOG.info("Saving settings");
            settings.save(Constants.CONFIG_PATH);
        } catch (Exception e) {
            App.LOG.error("Failed to write settings :(");
            App.LOG.catching(e);
        }
    }

}
