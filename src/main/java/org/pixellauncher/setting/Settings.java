package org.pixellauncher.setting;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.pixellauncher.App;
import org.pixellauncher.theme.Theme;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Data
public class Settings {

    private Theme theme;

    public Settings() {
        theme = Theme.DARK;
    }

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
