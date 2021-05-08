package connection;

import locale.ServerBundle;
import log.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ConnectionListenerImpl implements ConnectionListener {
    private ServerSocketChannel serverChannel;

    public void openConnection(String address, int port) throws IOException{
        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(address, port));
        Log.getLogger().info(ServerBundle.getFormattedString("server.created_channel", port));
    }

    public SocketChannel listen() throws IOException {
        return serverChannel.accept();
    }

    public void stop() throws IOException{
        serverChannel.socket().close();
        serverChannel.close();
    }

}
