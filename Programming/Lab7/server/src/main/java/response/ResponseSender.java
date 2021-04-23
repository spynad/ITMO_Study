package response;

import transferobjects.Response;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface ResponseSender {
    void sendResponse(SocketChannel socket, Response response) throws IOException, ClassNotFoundException;
}
