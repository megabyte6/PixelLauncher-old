package org.pixellauncher.util;

import org.pixellauncher.Constants;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public enum OS {

    WINDOWS, MAC_OS, LINUX;

    public static OS getOS() {
        final String name = getName().toLowerCase();

        if (name.contains("win")) {
            return OS.WINDOWS;
        } else if (name.contains("mac")) {
            return OS.MAC_OS;
        } else {
            return OS.LINUX;
        }
    }

    public static String getName() {
        return System.getProperty("os.name");
    }

    public static String getVersion() {
        return System.getProperty("os.version");
    }

    public static boolean isWindows() {
        return getOS() == WINDOWS;
    }

    public static boolean isMacOS() {
        return getOS() == MAC_OS;
    }

    public static boolean isLinux() {
        return getOS() == LINUX;
    }

    public static Path getStoragePath() {
        final Path portableStoragePath = Path.of("data");
        if (Files.exists(portableStoragePath))
            return portableStoragePath;

        return switch (getOS()) {
            case WINDOWS -> Paths.get(System.getenv("APPDATA"))
                    .resolve(Constants.LAUNCHER_NAME);
            case MAC_OS -> Paths.get(System.getProperty("user.home"))
                    .resolve("Library").resolve("Application Support")
                    .resolve("." + Constants.LAUNCHER_NAME.replaceAll("\\s+", "").toLowerCase());
            case LINUX -> Paths.get(System.getProperty("user.home"))
                    .resolve("." + Constants.LAUNCHER_NAME.replaceAll("\\s+", "").toLowerCase());
        };
    }

    public static boolean createStorageDirectory() {
        if (!Files.exists(Constants.STORAGE_PATH)) {
            return Constants.STORAGE_PATH.toFile().mkdirs();
        }
        return true;
    }

    public static boolean isUsingFlatpak() {
        return OS.isLinux() && Files.exists(Path.of("/.flatpak-info"));
    }

}
