package commands;

import client.AuthModule;
import exception.CommandExecutionException;
import locale.ClientLocale;

import java.io.IOException;

public class AuthCommand extends AbstractCommand{
    private final AuthModule authModule;

    public AuthCommand(AuthModule authModule) {
        this.authModule = authModule;
    }

    @Override
    public void execute() throws CommandExecutionException {

    }
}
