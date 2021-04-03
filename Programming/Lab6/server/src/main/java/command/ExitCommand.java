package command;

import connection.ConnectionListener;
import file.RouteWriter;
import server.Application;

import java.io.IOException;

/**
 * Класс-команда, реализующая выход из JVM
 */
public class ExitCommand implements ServerCommand {
    private final RouteWriter routeWriter;
    private final ConnectionListener connectionListener;
    private final Application application;

    public ExitCommand(RouteWriter routeWriter, ConnectionListener connectionListener, Application application) {
        this.routeWriter = routeWriter;
        this.connectionListener = connectionListener;
        this.application = application;
    }

    public void execute() {
        routeWriter.write();
        try {
            application.setIsRunning(false);
            connectionListener.stop();
        } catch (IOException ignored) {

        }
    }
}
