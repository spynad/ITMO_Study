package command;

import commands.AbstractCommand;
import commands.Command;

/**
 * Класс-команда, реализующая вывод всех команд, доступных для выполнения
 */
public class HelpCommand extends AbstractCommand implements Command {
    public HelpCommand(boolean req) {
        super(req);
    }

    public void execute() {
        System.out.println("help : вывести справку по доступным командам");
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("add {name, distance} : добавить новый элемент в коллекцию, после ввода команда будет запрашивать аргументы коллекции");
        System.out.println("update id {element} : обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_by_id id : удалить элемент из коллекции по его id");
        System.out.println("clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла.");
        System.out.println("exit : завершить программу (без сохранения в файл)");
        System.out.println("remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)");
        System.out.println("remove_first : удалить первый элемент из коллекции");
        System.out.println("history : вывести последние 11 команд (без их аргументов)");
        System.out.println("remove_all_by_distance distance : удалить из коллекции все элементы, значение поля distance которого эквивалентно заданному");//+
        System.out.println("sum_of_distance : вывести сумму значений поля distance для всех элементов коллекции");
        System.out.println("filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку");
    }
}
