package client;

import connection.ConnectionManager;
import hash.SHA224Generator;
import io.UserIO;
import request.RequestSender;
import response.ResponseReader;
import route.Request;
import route.RequestType;
import route.Response;
import user.User;

import java.io.IOException;
import java.nio.channels.SocketChannel;

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

    public User authorize() throws IOException {
        Request request;
        String username, password;
        userIO.printLine("Welcome!");
        while (true) {
            userIO.printLine("Enter login:");
            username = userIO.readLine();
            userIO.printLine("Enter password:");
            password = userIO.readLine();
            user = new User(username, SHA224Generator.getHash(password));
            request = new Request(RequestType.AUTH_REQUEST, user);
            requestSender.sendRequest(connectionManager.getConnection(), request);
            try {
                Response response = reader.getResponse(connectionManager.getConnection());
                if (response.isSuccess()) {
                    userIO.printLine("Successful auth");
                    return user;
                } else {
                    userIO.printLine("Invalid username or password");
                }
            } catch (ClassNotFoundException ignored) {}

        }
    }
}
