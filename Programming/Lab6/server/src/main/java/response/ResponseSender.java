package response;

import log.Log;
import route.Request;
import route.Response;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ResponseSender {
    private Selector selector;

    public void sendResponse(Selector selector, Response response) throws IOException, ClassNotFoundException{
        this.selector = selector;
        sendBytes(serializeResponse(response));
    }

    private void sendBytes(byte[] bytes) throws IOException {
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        SocketChannel channel = null;
        while (channel == null) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();

            //TODO: чекнуть позже хрень с итераторами
            while(iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isWritable()) {
                    channel = (SocketChannel)key.channel();
                    //buf.flip();
                    channel.write(buf);
                    //channel.register(selector, SelectionKey.OP_READ);
                    //key.cancel();
                }
                iterator.remove();
            }
        }
    }

    private byte[] serializeResponse(Response response) throws IOException{
        Log.getLogger().info("Serializing response");
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(byteStream);
        stream.writeObject(response);
        return byteStream.toByteArray();
    }
}
