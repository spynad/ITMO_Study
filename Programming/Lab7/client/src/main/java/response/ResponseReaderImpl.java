package response;

import transferobjects.Response;

import java.io.*;
import java.nio.channels.SocketChannel;

public class ResponseReaderImpl implements ResponseReader{

    public Response getResponse(SocketChannel socketChannel) throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[16384];
        InputStream stream = socketChannel.socket().getInputStream();
        stream.read(bytes);
        return deserializeRequest(bytes);
    }

    private Response deserializeRequest(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
        ObjectInputStream stream = new ObjectInputStream(byteStream);
        return (Response) stream.readObject();
    }
}
