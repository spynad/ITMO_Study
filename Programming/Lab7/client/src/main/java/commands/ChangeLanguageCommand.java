package commands;

import exception.CommandExecutionException;
import io.UserIO;
import locale.ClientLocale;

import java.io.IOException;
import java.util.Locale;
import java.util.Set;

public class ChangeLanguageCommand extends AbstractCommand{
    private final UserIO userIO;

    public ChangeLanguageCommand(UserIO userIO) {
        this.userIO = userIO;
    }

    @Override
    public void execute() throws CommandExecutionException {
        userIO.printLine(ClientLocale.getString("client.select_language"));
        Set<Locale> resourceBundles = ClientLocale.getResourceBundles();
        for (Locale rb : resourceBundles) {
            userIO.printLine(rb.getLanguage());
        }
        String selectedLanguage = "";
        try {
            selectedLanguage = userIO.readLine().toLowerCase(Locale.ROOT).trim();
        } catch (IOException e) {
            throw new CommandExecutionException(ClientLocale.getString("exception.general"));
        }
        for (Locale locale : resourceBundles) {
            if (locale.getLanguage().equals(selectedLanguage)) {
                Locale.setDefault(locale);
                return;
            }
        }
        throw new CommandExecutionException(ClientLocale.getString("client.unknown_language"));

    }
}
