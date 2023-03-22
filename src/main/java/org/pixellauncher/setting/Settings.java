package org.pixellauncher.setting;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

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
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), this);
    }

    public static Settings load(Path path) throws IOException {
        if (Files.isDirectory(path) || !Files.isReadable(path))
            return new Settings();

        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(path.toFile(), Settings.class);
    }

    public static Settings loadElseDefault(Path path) {
        Settings settings = new Settings();
        try {
            settings = load(path);
        } catch (Exception e) {
            System.err.println("WARNING: Settings failed to load.");
            e.printStackTrace();
        }

        return settings;
    }

}
