package org.pixellauncher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Scene scene = ResourceLoader.loadFXML("fxml/Main.fxml").load();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Pixel Launcher");
        primaryStage.show();
    }

}
