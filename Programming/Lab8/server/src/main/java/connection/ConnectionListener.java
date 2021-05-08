package connection;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface ConnectionListener {
    void openConnection(String address, int port) throws IOException;
    SocketChannel listen() throws IOException;
    void stop() throws IOException;
}
