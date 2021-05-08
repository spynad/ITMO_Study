package locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class ServerBundle {
    public static String getString(final String key) {
        return getString(key, Locale.getDefault());
    }

    public static String getString(final String key, final Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle("server", locale);
        return rb.getString(key);
    }

    public static String getFormattedString(final String key, Object... objects) {
        return String.format(getString(key, Locale.getDefault()), objects);
    }

    public static String getFormattedString(final String key, final Locale locale, Object... objects) {
        ResourceBundle rb = ResourceBundle.getBundle("server", locale);
        return String.format(rb.getString(key), objects);
    }
}
