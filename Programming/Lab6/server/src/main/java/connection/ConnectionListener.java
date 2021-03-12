package connection;

import log.Log;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ConnectionListener {
    private Selector selector;
    private ServerSocketChannel serverChannel;
    private SocketChannel connectedChannel;

    public void setChannel(ServerSocketChannel channel) {
        this.serverChannel = channel;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
        Log.getLogger().info("Set channel to " + this.selector.toString());
    }

    public Selector listen() throws IOException {
        selector.select();
        Set<SelectionKey> keys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = keys.iterator();

        while(iterator.hasNext()) {
            SelectionKey key = iterator.next();
            if (key.isAcceptable()) {
                connectedChannel = serverChannel.accept();
                connectedChannel.configureBlocking(false);
                connectedChannel.register(selector, SelectionKey.OP_READ);
                Log.getLogger().info("Accepted connection from the client " + connectedChannel.getRemoteAddress());
            }
            iterator.remove();
        }

        return selector;
    }

    public void stop() throws IOException{
        selector.close();
        serverChannel.socket().close();
        serverChannel.close();
    }
}
