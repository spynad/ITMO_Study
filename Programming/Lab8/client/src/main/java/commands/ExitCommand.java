package commands;

import client.Client;

public class ExitCommand extends AbstractCommand{
    Client client;


    public ExitCommand(Client client) {
        this.client = client;
    }

    @Override
    public void execute() {
        client.setIsRunning(false);

    }
}
