package commands;

import client.Client;
import exception.*;
import io.ScriptRouteParser;
import io.UserIO;
import locale.ClientLocale;
import transferobjects.Response;

import javax.validation.ValidatorFactory;
import java.io.*;

public class ExecuteScriptCommand extends AbstractCommand {
    private CommandInvoker commandInvoker;
    private Client client;
    private UserIO userIO;
    private String[] args;
    private String fileName;
    private ValidatorFactory validatorFactory;

    public ExecuteScriptCommand(Client client, CommandInvoker commandInvoker, UserIO userIO, ValidatorFactory validatorFactory) {
        this.client = client;
        this.commandInvoker = commandInvoker;
        this.userIO = userIO;
        this.validatorFactory = validatorFactory;
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
                        Response response = client.communicateWithServer(commands, new ScriptRouteParser(reader, userIO, validatorFactory));
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
