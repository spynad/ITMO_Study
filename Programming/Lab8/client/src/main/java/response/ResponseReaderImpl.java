package response;

import transferobjects.Request;
import transferobjects.Response;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class ResponseReaderImpl implements ResponseReader{
    Response response;

    public Response getResponse(SocketChannel socketChannel) throws IOException, ClassNotFoundException {
        ArrayList<ByteBuffer> buffers = new ArrayList<>();
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        int numRead = 0;
        while (true) {
            numRead = socketChannel.read(byteBuffer);
            if (!byteBuffer.hasRemaining()) {
                buffers.add(byteBuffer);
                byteBuffer = ByteBuffer.allocate(2048);
                continue;
            }
            else if (byteBuffer.position() != 0) {
                buffers.add(byteBuffer);
            }
            if (deserializeRequest(buffers)) {
                return response;
            }
        }

    }

    private boolean deserializeRequest(List<ByteBuffer> bufferList) throws IOException, ClassNotFoundException {
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
            Response response = (Response) stream.readObject();
            this.response = response;
        } catch (InvalidClassException | StreamCorruptedException | EOFException e) {
            return false;
        }
        return true;
    }
}
