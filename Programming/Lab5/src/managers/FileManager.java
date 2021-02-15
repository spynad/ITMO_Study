package managers;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;

import csv.CSVParser;
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
    private FileInputStream inputStream;
    private BufferedInputStream buffer;
    /**
     * Массив со строчками файла
     */
    private ArrayList<String> readResult;
    private FileWriter fileWriter;
    private PrintWriter printWriter;

    /**
     * Конструктор FileManager
     * @param routeManager - инстанция {@link RouteManager}
     */
    public FileManager(IRouteManager routeManager) {
        Log.logger.log(Level.INFO,"FileManager init");
        this.routeManager = routeManager;
    }

    /**
     * Метод, читающий файл и возвращающий ArrayList строчек файла
     * @param fileName - имя файла
     * @return - массив со строчками файла
     */
    public ArrayList<String> readFile(String fileName) {
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
            System.err.println("The file not found: " + fnfe);
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

    /**
     * Метдо, записывающий csv-строчку в файл
     */
    public void writeFile() {
        try {
            Log.logger.log(Level.INFO,"Writing csv string to csv file");
            fileWriter = new FileWriter("2.csv");
            printWriter = new PrintWriter(fileWriter);
            Parser csvParser = new CSVParser();
            String output = csvParser.makeFileFromRoute(routeManager.getRoutes());
            printWriter.print(output);

        } catch(IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        } finally {
            try {
                if (fileWriter != null && printWriter != null) {
                    printWriter.close();
                    fileWriter.close();
                }
            } catch (IOException ioe) {
                System.err.println("An exception occurred while close files: " + ioe);
            }
        }
    }
}
