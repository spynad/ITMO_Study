package commands;

import client.Application;
import exception.*;
import io.ScriptRouteParser;
import io.UserIO;
import locale.ClientLocale;
import transferobjects.Response;

import java.io.*;

public class ExecuteScriptCommand extends AbstractCommand {
    CommandInvoker commandInvoker;
    Application application;
    UserIO userIO;
    String[] args;
    String fileName;

    public ExecuteScriptCommand(Application application, CommandInvoker commandInvoker, UserIO userIO) {
        this.application = application;
        this.commandInvoker = commandInvoker;
        this.userIO = userIO;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args.clone();
    }

    @Override
    public void execute() throws CommandExecutionException {
        try {
            if (args.length == 1) {
                fileName = args[0];
            } else {
                throw new InvalidArgumentException(String.format(ClientLocale.getString("exception.expected_got"), 1, args.length));
            }
        } catch (InvalidArgumentException iae) {
            throw new CommandExecutionException(iae.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            if (commandInvoker.getScripts().contains(fileName)) {
                throw new IllegalStateException(ClientLocale.getString("exception.recursive_script_call")+": " + fileName);
            }
            commandInvoker.addScript(fileName);

            while (reader.ready()) {
                String commands = reader.readLine();
                try {
                    commandInvoker.execute(commands, null);
                } catch (CommandNotFoundException e) {
                    try {
                        Response response = application.communicateWithServer(commands, new ScriptRouteParser(reader, userIO));
                        userIO.printLine(response.getMessage());
                    } catch (EOFException eofe) {
                        userIO.printErrorMessage(ClientLocale.getString("exception.too_many_bytes"));
                    } catch (IOException | ClassNotFoundException ioe) {
                        userIO.printErrorMessage(ClientLocale.getString("exception.general_network")+": " + ioe.getMessage());
                    } catch (IllegalStateException ex) {
                        userIO.printErrorMessage(e.getMessage());
                    }
                } catch (CommandExecutionException executionException) {
                    userIO.printErrorMessage(ClientLocale.getString("exception.command_exec_error") +
                            ": " + executionException.getMessage());
                }
            }
            commandInvoker.removeScript(fileName);
        } catch (IllegalStateException ise) {
            userIO.printErrorMessage(ClientLocale.getString("exception.script_exec_error"));
        } catch (FileNotFoundException fnfe) {
            userIO.printErrorMessage(ClientLocale.getString("exception.script_not_found")+": " + fileName);
        } catch (IOException e) {
            userIO.printErrorMessage(ClientLocale.getString("exception.script_read_error")+": " + e.getMessage());
        }
    }
}
