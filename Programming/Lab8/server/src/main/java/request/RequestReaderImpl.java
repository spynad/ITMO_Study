package request;

import locale.ServerBundle;
import log.Log;
import transferobjects.Request;
import transferobjects.Response;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class RequestReaderImpl implements RequestReader{
    private Request request;
    @Override
    public Request getRequest(SelectionKey selectionKey) throws IOException, ClassNotFoundException {
        /*ByteBuffer buffer = ByteBuffer.allocate(8192);
        buffer.clear();
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

        int numRead = 1;
        try {
            while (numRead > 0) {
                numRead = socketChannel.read(buffer);
            }
        } catch (IOException e) {
            selectionKey.cancel();
            socketChannel.close();
        }
        Request request = deserializeRequest(buffer.array());
        request.setSocketChannel(socketChannel);
        return request;*/
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ArrayList<ByteBuffer> buffers = new ArrayList<>();
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        int numRead = 0;
        try {
            while (true) {
                numRead = socketChannel.read(byteBuffer);
                if (!byteBuffer.hasRemaining()) {
                    buffers.add(byteBuffer);
                    byteBuffer = ByteBuffer.allocate(2048);
                    continue;
                } else if (byteBuffer.position() != 0) {
                    buffers.add(byteBuffer);
                }
                if (deserializeRequest(buffers)) {
                    request.setSocketChannel(socketChannel);
                    return request;
                }
            }
        } catch (IOException e) {
            selectionKey.cancel();
            socketChannel.close();
        }
        return null;
    }

    private boolean deserializeRequest(List<ByteBuffer> bufferList) throws IOException, ClassNotFoundException{
        /*Log.getLogger().info(ServerBundle.getString("server.deserialization_request"));
        ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return (Request) stream.readObject();*/
        int bytesCount = 10;
        for (ByteBuffer byteBuffer : bufferList) {
            bytesCount += byteBuffer.position();
        }
        ByteBuffer buffer = ByteBuffer.allocate(bytesCount);
        for (ByteBuffer byteBuffer : bufferList) {
            int bufBytes = byteBuffer.position();
            byteBuffer.flip();
            for (int i = 0; i < bufBytes; i++) {
                buffer.put(byteBuffer.get());
            }
        }
        byte[] bytes = buffer.array();
        try {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
            ObjectInputStream stream = new ObjectInputStream(byteStream);
            Request request = (Request) stream.readObject();
            this.request = request;
        } catch (InvalidClassException | StreamCorruptedException | EOFException e) {
            return false;
        }
        return true;
    }


}
