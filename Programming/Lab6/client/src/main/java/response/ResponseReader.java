package response;

import route.Response;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ResponseReader {
    private SocketChannel socketChannel;
    InputStream stream;

    public Response getResponse(SocketChannel socketChannel) throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[16384];
        this.socketChannel = socketChannel;
        stream = socketChannel.socket().getInputStream();
        stream.read(bytes);
        return deserializeRequest(bytes);
        //ObjectInputStream stream = new ObjectInputStream(byteStream);
    }

    private Response deserializeRequest(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
        ObjectInputStream stream = new ObjectInputStream(byteStream);
        return (Response) stream.readObject();
    }

    /*private byte[] getBytes() throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(4096);
        buf.clear();
        //buf.flip();
        socketChannel.read(buf);
        return buf.array();
    }*/
}
