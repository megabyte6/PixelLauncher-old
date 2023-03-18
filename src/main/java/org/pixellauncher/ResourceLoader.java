package org.pixellauncher;

import java.io.InputStream;
import java.net.URL;
import javafx.fxml.FXMLLoader;

public class ResourceLoader {

    private ResourceLoader() {}

    public static URL loadURL(String path) {
        return ResourceLoader.class.getResource(path);
    }

    public static InputStream loadStream(String path) {
        return ResourceLoader.class.getResourceAsStream(path);
    }

    public static FXMLLoader loadFXML(String path) {
        return new FXMLLoader(loadURL(path));
    }

}
