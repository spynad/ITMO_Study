package log;

import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {
    public static Logger logger;

    static {
        try(FileInputStream ins = new FileInputStream("log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
            logger = Logger.getLogger(Log.class.getName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
