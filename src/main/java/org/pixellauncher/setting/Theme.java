package org.pixellauncher.setting;

import org.pixellauncher.ResourceLoader;

import java.net.URL;

public enum Theme {

    LIGHT, DARK;

    public static URL getStyleSheet(Theme theme) {
        return ResourceLoader.loadURL(switch (theme) {
            case LIGHT -> "themes/light.css";
            case DARK -> "themes/dark.css";
        });
    }

}
