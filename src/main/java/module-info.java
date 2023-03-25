module org.pixellauncher {
    // Modules from plugins.
    requires static lombok;

    requires javafx.controls;
    requires javafx.fxml;
    opens org.pixellauncher.controller to javafx.fxml;

    // Modules from dependencies.
    requires org.kordamp.ikonli.javafx;

    requires com.fasterxml.jackson.databind;
    opens org.pixellauncher.setting to com.fasterxml.jackson.databind;
    opens org.pixellauncher.ui to com.fasterxml.jackson.databind;

    requires org.apache.logging.log4j;

    requires MaterialFX;

    // Export main package.
    exports org.pixellauncher;
}
