package request;

import log.Log;
import route.Request;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class RequestReader {
    private Selector selector;

    public Request getRequest(Selector selector) throws IOException, ClassNotFoundException{
        this.selector = selector;
        return deserializeRequest(readBytes());
    }

    private byte[] readBytes() throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        SocketChannel channel = null;
        while (channel == null) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();

            //TODO: чекнуть позже хрень с итераторами
            while(iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isReadable()) {
                    channel = (SocketChannel)key.channel();
                    channel.read(buf);
                    //buf.flip();
                    channel.register(selector, SelectionKey.OP_WRITE);
                    //key.cancel();
                }
                iterator.remove();
            }
        }
        return buf.array();
    }

    private Request deserializeRequest(byte[] bytes) throws IOException, ClassNotFoundException{
        Log.getLogger().info("Deserializing request");
        ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return (Request) stream.readObject();
    }
}