package org.bot;

import java.util.ResourceBundle;

public class ConfigUtil {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("application");

    public static String getToken() {
        return bundle.getString("api.token");
    }
}
