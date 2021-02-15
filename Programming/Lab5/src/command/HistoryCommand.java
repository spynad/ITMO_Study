package command;

import java.util.Stack;

/**
 * Класс-команда, реализующая вывод истории последних 11 команд
 */
public class HistoryCommand implements Command{
    Stack<String> history;

    HistoryCommand(Stack<String> history) {
        this.history = history;
    }

    public void execute() {
        for (int i = history.size() - 1; i >= 0; i--) {
            System.out.println(history.get(i));
        }
    }
}
