package request;

import route.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RequestSender {
    private SocketChannel socketChannel;

    public void sendRequest(SocketChannel socketChannel, Request request) throws IOException {
        this.socketChannel = socketChannel;
        sendBytes(serialiazeRequest(request));
    }

    private byte[] serialiazeRequest(Request request) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(byteStream);
        stream.writeObject(request);
        return byteStream.toByteArray();
    }

    private void sendBytes(byte[] bytes) throws IOException {
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        while (buf.hasRemaining()) {
            socketChannel.write(buf);
        }
    }
}
