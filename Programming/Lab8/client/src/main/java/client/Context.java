package client;

import commands.CommandInvoker;
import connection.ConnectionManager;
import connection.ConnectionManagerImpl;
import controller.SceneLoader;
import io.ConsoleIO;
import io.JavaFXIO;
import io.UserIO;
import request.RequestCreator;
import request.RequestSender;
import request.RequestSenderImpl;
import response.ResponseReader;
import response.ResponseReaderImpl;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

public class Context {
    private AuthModule authModule;
    private CommandInvoker commandInvoker;
    private ConnectionManager connectionManager;
    private RequestCreator requestCreator;
    private RequestSender requestSender;
    private ResponseReader responseReader;
    private SceneLoader sceneLoader;
    private UserIO userIO;
    private ValidatorFactory validatiorFactory;

    public AuthModule getAuthModule() {
        if (authModule == null) {
            authModule = new AuthModule(getUserIO(), getConnectionManager(), getRequestSender(), getResponseReader());
        }
        return authModule;
    }

    public CommandInvoker getCommandInvoker() {
        if (commandInvoker == null) {
            commandInvoker = new CommandInvoker();
        }
        return commandInvoker;
    }

    public ConnectionManager getConnectionManager() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerImpl();
        }
        return connectionManager;
    }

    public RequestCreator getRequestCreator() {
        if (requestCreator == null) {
            requestCreator = new RequestCreator(getAuthModule());
        }
        return requestCreator;
    }

    public RequestSender getRequestSender() {
        if (requestSender == null) {
            requestSender = new RequestSenderImpl();
        }
        return requestSender;
    }

    public ResponseReader getResponseReader() {
        if (responseReader == null) {
            responseReader = new ResponseReaderImpl();
        }
        return responseReader;
    }

    public UserIO getUserIO() {
        if (userIO == null) {
            userIO = new JavaFXIO();
        }
        return userIO;
    }

    public ValidatorFactory getValidationFactory() {
        if (validatiorFactory == null) {
            validatiorFactory = Validation.buildDefaultValidatorFactory();
        }
        return validatiorFactory;
    }
}
