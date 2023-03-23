package org.pixellauncher.theme;

import org.pixellauncher.ResourceLoader;

import java.net.URL;

public enum Theme {

    LIGHT, DARK;

    public static URL getStyleSheet(Theme theme) {
        return ResourceLoader.loadURL(switch (theme) {
            case LIGHT -> "theme/light.css";
            case DARK -> "theme/dark.css";
        });
    }

}
