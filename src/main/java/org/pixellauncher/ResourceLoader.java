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
        return ResourceLoader.class.getResource("/assets/" + path);
    }

    /**
     * @param path The location of the resource to load. Excluding the
     *             {@code assets} directory.
     * @return An {@link InputStream} of the resource.
     */
    public static InputStream loadStream(String path) {
        return ResourceLoader.class.getResourceAsStream("/assets/" + path);
    }

    /**
     * @param path The location of the resource to load. Excluding the
     *             {@code assets} directory.
     * @return A {@link String} of the {@link URL} of the path.
     */
    public static String load(String path) {
        return loadURL(path).toString();
    }

    /**
     * Call {@code load()} to load the fxml and call the controller.
     *
     * @param path The location of the {@code fxml} file to load. Excluding the
     *             {@code assets} and {@code fxml} directories.
     * @return An {@link FXMLLoader} representing the {@code fxml} file
     *         given.
     */
    public static FXMLLoader loadFXML(String path) {
        if (!path.endsWith(".fxml"))
            path += ".fxml";
        return new FXMLLoader(loadURL("fxml/" + path));
    }

}
