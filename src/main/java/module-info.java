module org.pixellauncher {
    requires static lombok;

    requires javafx.graphics;
    requires transitive javafx.fxml;

    opens org.pixellauncher.controller to javafx.fxml;

    exports org.pixellauncher;
}
