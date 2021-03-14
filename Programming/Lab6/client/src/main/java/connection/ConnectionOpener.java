package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class ConnectionOpener {
    public SocketChannel socketChannel;

    public SocketChannel openConnection(String address, int port) throws IOException {
        socketChannel = SocketChannel.open(new InetSocketAddress(address, port));
        return socketChannel;
    }

    public void closeConnection() throws IOException{
        socketChannel.close();
    }
}
