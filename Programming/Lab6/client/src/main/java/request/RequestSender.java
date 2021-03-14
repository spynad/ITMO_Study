package request;

import route.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RequestSender {
    private SocketChannel socketChannel;
    OutputStream stream;

    public void initOutputStream(SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
        stream = socketChannel.socket().getOutputStream();
    }

    public void sendRequest(SocketChannel socketChannel, Request request) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(byteStream);
        stream.writeObject(request);
        this.stream.write(byteStream.toByteArray());
        this.stream.flush();
//        sendBytes(serialiazeRequest(request));
    }

   /* private byte[] serialiazeRequest(Request request) throws IOException {
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
    }*/
}
