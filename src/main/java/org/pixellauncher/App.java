package org.pixellauncher;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.pixellauncher.constant.Constants;
import org.pixellauncher.setting.Settings;
import org.pixellauncher.setting.Theme;

public class App extends Application {

    @Getter
    @Setter
    private static Settings settings;

    public static void main(String[] args) {
        settings = Settings.loadElseDefault(Constants.CONFIG_PATH);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Parent root = ResourceLoader.loadFXML("fxml/Main.fxml").load();
        final Scene scene = new Scene(root);

        final String styleSheetPath = Theme.getStyleSheet(getSettings().getTheme()).toString();
        scene.getStylesheets().setAll(styleSheetPath);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(ResourceLoader.loadStream("logo.png")));
        primaryStage.setTitle(Constants.LAUNCHER_NAME_FORMATTED);
        primaryStage.show();
    }

}
