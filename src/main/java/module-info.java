module com.megabyte6.pixellauncher {
    requires static lombok;

    requires transitive javafx.controls;
    requires transitive javafx.fxml;

    exports org.pixellauncher;
}
