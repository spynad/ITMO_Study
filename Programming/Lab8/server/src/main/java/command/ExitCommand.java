package command;

import connection.ConnectionListener;
import server.Application;
import server.Server;

import java.io.IOException;

/**
 * Класс-команда, реализующая выход из JVM
 */
public class ExitCommand implements ServerCommand {
    private final ConnectionListener connectionListener;
    private final Application application;
    private final Server server;

    public ExitCommand(ConnectionListener connectionListener, Application application, Server server) {
        this.connectionListener = connectionListener;
        this.application = application;
        this.server = server;
    }

    public synchronized void execute() {
        try {
            application.setIsRunning(false);
            server.shutdownExecutorServices();
            connectionListener.stop();
        } catch (IOException ignored) {

        }
    }
}
