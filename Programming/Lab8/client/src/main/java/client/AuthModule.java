package client;

import connection.ConnectionManager;
import io.UserIO;
import locale.ClientLocale;
import request.RequestSender;
import response.ResponseReader;
import transferobjects.Request;
import transferobjects.RequestType;
import transferobjects.Response;
import user.User;

import java.io.IOException;

public class AuthModule {
    private final UserIO userIO;
    private final ConnectionManager connectionManager;
    private final RequestSender requestSender;
    private final ResponseReader reader;
    private User user;

    public AuthModule(UserIO userIO, ConnectionManager connectionManager, RequestSender requestSender, ResponseReader reader) {
        this.userIO = userIO;
        this.connectionManager = connectionManager;
        this.requestSender = requestSender;
        this.reader = reader;
    }

    public void authorize() throws IOException {
        userIO.printLine(ClientLocale.getString("client.username_prompt"));
        String username = userIO.readLine();
        userIO.printLine(ClientLocale.getString("client.password_prompt"));
        String password = userIO.readPasswordLine();
        user = new User(username, password);
        Request request = new Request(RequestType.AUTH_REQUEST, user);
        requestSender.sendRequest(connectionManager.getConnection(), request);
        try {
            Response response = reader.getResponse(connectionManager.getConnection());
            if (response.isSuccess()) {
                userIO.printLine(ClientLocale.getString("client.successful_auth"));
            } else {
                userIO.printLine(response.getMessage());
                user = null;
            }
        } catch (ClassNotFoundException ignored) {}
    }

    public void register() throws IOException {
        userIO.printLine(ClientLocale.getString("client.username_prompt"));
        String username = userIO.readLine();
        userIO.printLine(ClientLocale.getString("client.password_prompt"));
        String password = userIO.readPasswordLine();
        if (password.length() < 7) {
            userIO.printErrorMessage(ClientLocale.getString("client.invalid_password"));
            return;
        }
        user = new User(username, password);
        Request request = new Request(RequestType.REGISTER_REQUEST, user);
        requestSender.sendRequest(connectionManager.getConnection(), request);
        try {
            Response response = reader.getResponse(connectionManager.getConnection());
            if (response.isSuccess()) {
                userIO.printLine(ClientLocale.getString("client.successful_registration"));
            } else {
                userIO.printLine(response.getMessage());
            }
        } catch (ClassNotFoundException ignored) {}
    }

    public User getUser() {
        return user;
    }
}
