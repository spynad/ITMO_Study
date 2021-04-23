package response;

import locale.ServerBundle;
import log.Log;
import transferobjects.Response;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ResponseSenderImpl implements ResponseSender{

    public void sendResponse(SocketChannel socket, Response response) throws IOException{
        log.Log.getLogger().info(ServerBundle.getString("server.sending_response"));
        sendBytes(serializeResponse(response), socket);
        log.Log.getLogger().info(ServerBundle.getString("server.response_sent"));
    }

    private void sendBytes(byte[] bytes, SocketChannel socket) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        socket.write(byteBuffer);
    }

    private byte[] serializeResponse(Response response) throws IOException{
        Log.getLogger().info(ServerBundle.getString("server.serializing_response"));
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(byteStream);
        stream.writeObject(response);
        stream.flush();
        return byteStream.toByteArray();
    }
}
