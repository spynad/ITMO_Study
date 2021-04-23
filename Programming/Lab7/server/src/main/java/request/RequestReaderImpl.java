package request;

import locale.ServerBundle;
import log.Log;
import transferobjects.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RequestReaderImpl implements RequestReader{


    public Request getRequest(SocketChannel socket) throws IOException, ClassNotFoundException{
        return deserializeRequest(readBytes(socket));
    }

    private byte[] readBytes(SocketChannel socketChannel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        socketChannel.read(byteBuffer);
        return byteBuffer.array();
    }

    private Request deserializeRequest(byte[] bytes) throws IOException, ClassNotFoundException{
        Log.getLogger().info(ServerBundle.getString("server.deserialization_request"));
        ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return (Request) stream.readObject();
    }
}
