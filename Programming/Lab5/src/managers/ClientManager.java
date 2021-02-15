package managers;

import command.CommandInvoker;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Класс, выполняющий основной цикл считывания и выполнения команд
 * @author spynad
 * @version govno
 */
public class ClientManager {
    CommandInvoker commandInvoker;
    private static BufferedReader input;

    /**
     * Конструктор - создает новую инстанцию ClassManager.
     * @param routeManager - инстанция RouteManager
     * @param fileManager - инстанция FileManager
     * @param input - ссылка на глоабльный поток ввода
     */
    public ClientManager(IRouteManager routeManager, IFileManager fileManager, BufferedReader input) {
        commandInvoker = new CommandInvoker(routeManager, fileManager);
        ClientManager.input = input;
    }

    /**
     * Функция прсваивания новго глобального потока ввода.
     * @param input - новый поток ввода
     */
    public static void setInput(BufferedReader input) {
        ClientManager.input = input;
    }

    /**
     * Метод, возвращающая глобальный поток ввода.
     * @return - глобальный поток ввода
     */
    public static BufferedReader getInput() {
        return input;
    }

    /**
     * Метод, запускающий основной цикл чтения и выполнения команд
     */
    public void start() {
        while(true) {
            try {
                System.out.print("> ");
                String str = input.readLine();
                commandInvoker.getCommand(str);
            } catch (IllegalStateException | IOException ise) {
                System.err.println(ise.getMessage());
            }

        }
    }
}
