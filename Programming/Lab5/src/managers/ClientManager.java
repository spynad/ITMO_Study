package managers;

import command.Command;
import command.CommandInvoker;
import log.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

/**
 * Класс, выполняющий основной цикл считывания и выполнения команд
 * @author spynad
 * @version govno
 */
public class ClientManager implements IClientManager {
    CommandInvoker commandInvoker;
    private static BufferedReader input;

    /**
     * Конструктор - создает новую инстанцию ClassManager.
     * @param routeManager - инстанция RouteManager
     * @param fileManager - инстанция FileManager
     */
    public ClientManager(IRouteManager routeManager, IFileManager fileManager) {
        commandInvoker = new CommandInvoker(routeManager, fileManager);
        input = new BufferedReader(new InputStreamReader(System.in));
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
    //TODO: вынести этот цикл из класс менеджера, он должен его выполнять из внешнего класса, например, Application
    public void start() {
        while(true) {
            try {
                System.out.print("> ");
                String str = input.readLine();
                Command command = commandInvoker.getCommand(str);
                commandInvoker.execute(command);
            } catch (IllegalStateException | IOException ise) {
                System.err.println(ise.getMessage());
                Log.logger.log(Level.WARNING, "EXCEPTION: ", ise);
            }

        }
    }
}
