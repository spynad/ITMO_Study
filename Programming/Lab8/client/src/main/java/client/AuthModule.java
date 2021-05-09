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

    public boolean authorize(String username, String password) throws IOException {
        user = new User(username, password);
        Request request = new Request(RequestType.AUTH_REQUEST, user);
        requestSender.sendRequest(connectionManager.getConnection(), request);
        try {
            Response response = reader.getResponse(connectionManager.getConnection());
            if (response.isSuccess()) {
                userIO.printLine(ClientLocale.getString("client.successful_auth"));
                return true;
            } else {
                userIO.printLine(response.getMessage());
                user = null;
                return false;
            }
        } catch (ClassNotFoundException ignored) { return false;}
    }

    public boolean register(String username, String password) throws IOException {
        if (password.length() < 7) {
            userIO.printErrorMessage(ClientLocale.getString("client.invalid_password"));
            return false;
        }
        user = new User(username, password);
        Request request = new Request(RequestType.REGISTER_REQUEST, user);
        requestSender.sendRequest(connectionManager.getConnection(), request);
        try {
            Response response = reader.getResponse(connectionManager.getConnection());
            if (response.isSuccess()) {
                userIO.printLine(ClientLocale.getString("client.successful_registration"));
                return true;
            } else {
                userIO.printLine(response.getMessage());
                return false;
            }
        } catch (ClassNotFoundException ignored) {return false;}
    }

    public User getUser() {
        return user;
    }
}
