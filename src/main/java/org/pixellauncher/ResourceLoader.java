package org.pixellauncher;

import javafx.fxml.FXMLLoader;

import java.io.InputStream;
import java.net.URL;

public class ResourceLoader {

    private ResourceLoader() {
    }

    /**
     * @param path The location of the resource to load. Excluding the
     *             {@code assets} directory.
     * @return A {@link URL} representing the resource.
     */
    public static URL loadURL(String path) {
        return ResourceLoader.class.getResource(path);
    }

    /**
     * @param path The location of the resource to load. Excluding the
     *             {@code assets} directory.
     * @return An {@link InputStream} of the resource.
     */
    public static InputStream loadStream(String path) {
        return ResourceLoader.class.getResourceAsStream(path);
    }

    /**
     * Call {@code load()} to load the fxml and call the controller.
     *
     * @param path The location of the {@code fxml} file to load. Excluding the
     *             {@code assets} directory.
     * @return An {@link FXMLLoader} representing the {@code fxml} file
     * given.
     */
    public static FXMLLoader loadFXML(String path) {
        return new FXMLLoader(loadURL(path));
    }

}
