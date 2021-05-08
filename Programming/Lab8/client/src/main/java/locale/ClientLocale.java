package locale;

import java.util.*;

public class ClientLocale {
    public static String getString(final String key) {
        return getString(key, Locale.getDefault());
    }

    public static String getString(final String key, final Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle("client", locale);
        return rb.getString(key);
    }

    public static Set<Locale> getResourceBundles() {
        Set<Locale> resourceBundles = new HashSet<>();

        for (Locale locale : Locale.getAvailableLocales()) {
            try {
                if (!locale.getLanguage().equals("")) {
                    resourceBundles.add(ResourceBundle.getBundle("client", locale).getLocale());
                }
            } catch (MissingResourceException ignored) {}
        }

        return Collections.unmodifiableSet(resourceBundles);
    }
}
