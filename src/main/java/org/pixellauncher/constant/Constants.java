package org.pixellauncher.constant;

import org.pixellauncher.util.OS;

import java.nio.file.Path;

public class Constants {

    public static final String LAUNCHER_NAME = "PixelLauncher";
    public static final String LAUNCHER_NAME_FORMATTED = "Pixel Launcher";
    public static final String LAUNCHER_WEBSITE = "https://github.com/megabyte6/PixelLauncher";

    public static final Path CONFIG_PATH = OS.getStoragePath().resolve("config.json");

}
