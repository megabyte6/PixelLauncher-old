package org.pixellauncher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Pane pane = ResourceLoader.loadFXML("fxml/Main.fxml").load();
        final Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Pixel Launcher");
        primaryStage.show();
    }

}
