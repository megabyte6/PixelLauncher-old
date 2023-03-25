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
import org.pixellauncher.ui.Theme;
import org.pixellauncher.util.OS;

public class App extends Application {

    public static final Logger LOGGER = LogManager.getLogger(App.class);

    @Getter
    private static Stage stage;

    @Getter
    @Setter
    private static Settings settings;

    public static void main(String[] args) {
        if (!OS.createStorageDirectory()) {
            App.LOGGER.fatal("Failed to create the app config and data directory.");
            return;
        }
        settings = Settings.loadElseDefault(Constants.CONFIG_PATH);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        App.stage = primaryStage;

        final Parent root = ResourceLoader.loadFXML("fxml/Main.fxml").load();
        final Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        // Load correct theme.
        final String styleSheetPath = Theme.getStyleSheet(getSettings().getTheme()).toString();
        scene.getStylesheets().setAll(styleSheetPath);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

        // Set persistent window properties.
        primaryStage.setX(getSettings().getLauncherPosition().getX());
        primaryStage.setY(getSettings().getLauncherPosition().getY());
        primaryStage.setWidth(getSettings().getLauncherSize().getWidth());
        primaryStage.setHeight(getSettings().getLauncherSize().getHeight());

        // Add other stage info.
        primaryStage.getIcons().add(new Image(ResourceLoader.loadStream("icon.png")));
        primaryStage.setTitle(Constants.LAUNCHER_NAME);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();

        writeSettings();
    }

    public static void writeSettings() {
        // Update settings with new window position and size.
        getSettings().setLauncherPosition(new Point2D(App.getStage().getX(), App.getStage().getY()));
        getSettings().setLauncherSize(new Dimension2D(App.getStage().getWidth(), App.getStage().getHeight()));

        try {
            settings.save(Constants.CONFIG_PATH);
        } catch (Exception e) {
            App.LOGGER.error("Failed to write settings.");
            App.LOGGER.catching(e);
        }
    }

}
