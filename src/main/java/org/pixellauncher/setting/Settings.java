package org.pixellauncher.setting;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.stage.Screen;
import lombok.Data;
import lombok.NonNull;
import org.pixellauncher.App;
import org.pixellauncher.ui.Theme;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Data
public class Settings {

    // Window settings.
    private Dimension2D launcherSize = new Dimension2D(1000, 600);
    private Point2D launcherPosition = new Point2D(
            (Screen.getPrimary().getBounds().getWidth() / 2) - (getLauncherSize().getWidth() / 2),
            (Screen.getPrimary().getBounds().getHeight() / 2) - (getLauncherSize().getHeight() / 2));

    // General settings.
    @NonNull
    private Theme theme = Theme.DARK;

    public void save(Path path) throws IOException {
        if (!Files.isWritable(path.getParent()))
            throw new IOException("Settings directory '" + path.getParent() + "' is not writable.");

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), this);
    }

    public static Settings load(Path path) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(path.toFile(), Settings.class);
    }

    public static Settings loadElseDefault(Path path) {
        Settings settings = new Settings();
        try {
            settings = load(path);
        } catch (Exception e) {
            App.LOGGER.info("Settings failed to load. Using default.");
        }

        return settings;
    }

}
