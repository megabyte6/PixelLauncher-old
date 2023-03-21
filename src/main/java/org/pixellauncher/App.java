package org.pixellauncher;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Parent root = ResourceLoader.loadFXML("fxml/Main.fxml").load();
        final Scene scene = new Scene(root);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(ResourceLoader.loadStream("logo.png")));
        primaryStage.setTitle("Pixel Launcher");
        primaryStage.show();
    }

}
