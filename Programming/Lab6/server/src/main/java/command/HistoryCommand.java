package command;

import commands.AbstractCommand;
import commands.Command;
import response.Creator;

import java.util.Stack;

/**
 * Класс-команда, реализующая вывод истории последних 11 команд
 */
public class HistoryCommand extends AbstractCommand implements Command {
    Creator creator;
    CommandInvoker commandInvoker

    HistoryCommand(CommandInvoker commandInvoker, Creator creator, boolean req) {
        super(req);
        this.commandInvoker = commandInvoker;
        this.creator = creator;
    }

    public void execute() {
        for (int i = history.size() - 1; i >= 0; i--) {
            creator.addToMsg(history.get(i));
        }
    }
}
