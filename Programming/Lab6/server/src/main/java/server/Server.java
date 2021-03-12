package server;

import log.Log;

public class Server {
    public static void main(String[] args) {
        Log.getLogger().info("Hello, world!");
        Application application = new Application();
        try {
            application.start();
        } catch (Exception e) {
            Log.getLogger().error(e);
        }
        /*try {
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.socket().bind(new InetSocketAddress(3110));

            while(true) {
                SocketChannel socketChannel = channel.accept();
                ByteBuffer buf = ByteBuffer.allocate(1024);
                buf.clear();
                socketChannel.read(buf);
                ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(buf.array()));
                String test = (String) stream.readObject();
                System.out.println(test);
            }
        } catch (IOException | ClassNotFoundException ioe) {
            System.err.println(ioe.getMessage());
        }*/
    }
}
