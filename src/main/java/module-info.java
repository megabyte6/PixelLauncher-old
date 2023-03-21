module org.pixellauncher {
    requires static lombok;

    requires MaterialFX;

    requires org.kordamp.ikonli.javafx;

    requires javafx.graphics;
    requires transitive javafx.fxml;

    opens org.pixellauncher.controller to javafx.fxml;

    exports org.pixellauncher;
}
