package request;

import transferobjects.Request;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface RequestReader {
    Request getRequest(SocketChannel socket) throws IOException, ClassNotFoundException;
}
