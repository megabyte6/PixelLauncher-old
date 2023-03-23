module org.pixellauncher {
    // Modules from plugins.
    requires static lombok;

    requires javafx.graphics;
    requires transitive javafx.fxml;
    opens org.pixellauncher.controller to javafx.fxml;

    // Modules from dependencies.
    requires org.kordamp.ikonli.javafx;

    requires transitive com.fasterxml.jackson.databind;
    exports org.pixellauncher.setting to com.fasterxml.jackson.databind;

    requires org.apache.logging.log4j;

    requires MaterialFX;

    exports org.pixellauncher;
}
