package command;

public class ExitCommand implements Command{

    public void execute() {
        System.exit(0);
    }
}
