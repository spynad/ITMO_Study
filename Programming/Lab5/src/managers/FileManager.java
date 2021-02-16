package managers;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;

import csv.CSVRouteParser;
import csv.Parser;
import log.Log;

/**
 * Класс, отвечающий за взаимодействие с файлами
 * @author spynad
 * @version govno
 */
public class FileManager implements IFileManager{
    /**
     * Инстанция {@link RouteManager}
     */
    private final IRouteManager routeManager;
    private final Parser csvParser;
    private FileInputStream inputStream;
    private BufferedInputStream buffer;
    /**
     * Массив со строчками файла
     */
    /*TODO: абсолютно бесполезное поле, необходимо избавиться от него, т.е. передавать его чере аргументы
      TODO: методов, так как только в одном методе он используется*/
    private ArrayList<String> readResult;
    private FileWriter fileWriter;
    private PrintWriter printWriter;

    /**
     * Название файла, с которым будет работать программа
     */
    private String fileName;

    /**
     * Конструктор FileManager
     * @param routeManager - инстанция {@link RouteManager}
     */
    public FileManager(IRouteManager routeManager, Parser csvParser) {
        Log.logger.log(Level.INFO,"FileManager init");
        this.routeManager = routeManager;
        this.csvParser = csvParser;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Метод, читающий файл и возвращающий ArrayList строчек файла
     * @return - массив со строчками файла
     */
    public ArrayList<String> readFile() {
        Log.logger.log(Level.INFO,"Reading file " + fileName);
        try {
            StringBuilder readStringBuilder = new StringBuilder();
            File file = new File(fileName);
            inputStream = new FileInputStream(file);
            buffer = new BufferedInputStream(inputStream);
            readResult = new ArrayList<>();

            while (buffer.available() > 0) {
                char nextChar = (char) buffer.read();
                readStringBuilder.append(nextChar);

                if (nextChar == '\n') {
                    readStringBuilder.delete(readStringBuilder.length() - 1, readStringBuilder.length());
                    if(!readStringBuilder.toString().equals(""))
                        readResult.add(readStringBuilder.toString());
                    readStringBuilder.delete(0, readStringBuilder.length());
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("The file not found, creating a new one.");
            createNewFile(fileName);
        } catch (IOException e) {
            System.err.println("An exception occurred while trying to read file: " + e);
        }
        finally {
            try {
                if (inputStream != null && buffer != null) {
                    buffer.close();
                    inputStream.close();
                }
            } catch (IOException ioe) {
                System.err.println("An exception occurred while close files: " + ioe);
            }
        }
        return readResult;
    }

    public void createNewFile(String fileName) {
        try {
            fileWriter = new FileWriter(fileName);
            printWriter = new PrintWriter(fileWriter);
            printWriter.println("id,name,coordinates,creationDate,from,to,distance");
            System.err.println("The file has been created, restart the application.");
        } catch (IOException ioe) {
            System.err.println("Exception while trying to create a new file");
        } finally {
            try {
                if (fileWriter != null && printWriter != null) {
                    printWriter.close();
                    fileWriter.close();
                    System.exit(0);
                }
            } catch (IOException ioe) {
                System.err.println("Error while trying to close streams");
            }
        }
    }

    /**
     * Метдо, записывающий csv-строчку в файл
     */
    public void writeFile() {
        try {
            Log.logger.log(Level.INFO,"Writing csv string to csv file");
            fileWriter = new FileWriter(fileName);
            printWriter = new PrintWriter(fileWriter);
            String output = csvParser.makeFileFromRoute(routeManager.getRoutes());
            printWriter.print(output);

        } catch(IOException ioe) {
            System.err.println("Exception while trying to write to a file");
            Log.logger.log(Level.WARNING, "EXCEPTION: ", ioe);
            System.exit(1);
        } finally {
            try {
                if (fileWriter != null && printWriter != null) {
                    printWriter.close();
                    fileWriter.close();
                }
            } catch (IOException ioe) {
                System.err.println("An exception occurred while close files: " + ioe);
                Log.logger.log(Level.WARNING, "EXCEPTION: ", ioe);
            }
        }
    }
}
