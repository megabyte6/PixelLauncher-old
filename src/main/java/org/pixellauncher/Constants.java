package org.pixellauncher;

import org.pixellauncher.util.OS;

import java.nio.file.Path;

public class Constants {

    public static final String LAUNCHER_NAME = "Pixel Launcher";
    public static final String LAUNCHER_WEBSITE = "https://github.com/megabyte6/PixelLauncher";

    public static final Path STORAGE_PATH = OS.getStoragePath();
    public static final Path CONFIG_PATH = STORAGE_PATH.resolve("config.json");
    public static final Path LOG_PATH = STORAGE_PATH.resolve("logs");

}
