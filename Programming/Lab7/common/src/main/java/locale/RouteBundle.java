package locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class RouteBundle {
    public static String getString(final String key) {
        return getString(key, Locale.getDefault());
    }

    public static String getString(final String key, final Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle("route", locale);
        return rb.getString(key);
    }
}
