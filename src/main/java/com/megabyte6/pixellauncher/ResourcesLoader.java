package com.megabyte6.pixellauncher;

import java.io.InputStream;
import java.net.URL;
import javafx.fxml.FXMLLoader;

public class ResourcesLoader {

    private ResourcesLoader() {}

    public static URL loadURL(String path) {
        return ResourcesLoader.class.getResource(path);
    }

    public static InputStream loadStream(String path) {
        return ResourcesLoader.class.getResourceAsStream(path);
    }

    public static FXMLLoader loadFXML(String path) {
        return new FXMLLoader(loadURL(path));
    }

}
