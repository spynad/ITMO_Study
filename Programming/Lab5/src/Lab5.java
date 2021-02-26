import main.Application;

public class Lab5 {

    public static void main(String[] args) {
        //TODO: если ввести пустую строчку в аргументе, например, "", тогда программа ничего не выведет, исправить
        if (args.length > 0) {
            if(!args[0].equals("")) {
                Application application = new Application();
                application.init(args[0]);
                application.loop();
            }
        }
        else {
            System.err.println("Usage: program_name fileName");
        }
    }
}
