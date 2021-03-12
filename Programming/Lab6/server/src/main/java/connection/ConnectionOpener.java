package connection;

import log.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class ConnectionOpener {
    private ServerSocketChannel currentChannel;

    public ServerSocketChannel getCurrentChannel() {
        return currentChannel;
    }

    public Selector getConnection(String address, int port) throws IOException{
        Selector selector = Selector.open();
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress(address, port));
        channel.configureBlocking(false);
        int ops = channel.validOps();
        SelectionKey key = channel.register(selector, ops, null);

        Log.getLogger().info("Created channel with listening port " + port);

        this.currentChannel = channel;
        return selector;
    }
}
