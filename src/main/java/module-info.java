module org.pixellauncher {
    requires static lombok;

    requires transitive javafx.controls;
    requires transitive javafx.fxml;

    opens org.pixellauncher.controller to javafx.fxml;

    exports org.pixellauncher;
}
