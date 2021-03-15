package client;

public class Client {
    public static void main(String[] args)  {
        Application application = new Application("localhost", 60000);
        application.start();
    }
}
