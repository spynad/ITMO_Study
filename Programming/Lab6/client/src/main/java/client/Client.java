package client;

public class Client {
    public static void main(String[] args)  {
        Application application = new Application();
        application.start("localhost", 2020);
    }
}
