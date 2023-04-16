package org.pixellauncher.setting;

import org.pixellauncher.ResourceLoader;

public enum Theme implements io.github.palexdev.materialfx.css.themes.Theme {

    LIGHT("themes/light/Light.css"),
    DARK("themes/dark/Dark.css");

    private final String themePath;

    Theme(String themePath) {
        this.themePath = themePath;
    }

    /**
     * @return The theme's path/url
     */
    @Override
    public String getTheme() {
        return themePath;
    }

    /**
     * @return The loaded theme as a String.
     */
    @Override
    public String loadTheme() {
        return ResourceLoader.load(getTheme());
    }

}
