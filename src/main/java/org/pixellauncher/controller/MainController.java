package org.pixellauncher.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.pixellauncher.App;
import org.pixellauncher.ResourceLoader;

import java.io.IOException;

public class MainController {

    @FXML
    private FlowPane instanceContainer;

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleAccountButton() {
        final Parent root;
        try {
            root = ResourceLoader.loadFXML("AccountManager").load();
        } catch (IOException ioe) {
            App.LOG.error("Unable to load AccountManager FXML file");
            App.LOG.catching(ioe);

            return;
        }

        final Stage stage = new Stage();
        stage.setTitle("Account Manager");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleSettingsButton() {
    }

}
