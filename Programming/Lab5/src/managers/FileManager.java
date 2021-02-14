package managers;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;

import csv.CSVParser;
import csv.Parser;
import log.Log;

public class FileManager implements IFileManager{
    private IRouteManager routeManager;
    private Parser parser;
    private File file;
    private FileInputStream inputStream;
    private BufferedInputStream buffer;
    private StringBuilder readStringBuilder;
    private ArrayList<String> readResult;

    public FileManager(IRouteManager routeManager, Parser parser) {
        Log.logger.log(Level.INFO,"FileManager init");
        this.routeManager = routeManager;
        this.parser = parser;
    }

    public ArrayList<String> readFile(String filePath) {
        Log.logger.log(Level.INFO,"Reading file " + filePath);
        try {
            CSVParser csvParser = new CSVParser();
            readStringBuilder = new StringBuilder();
            file = new File(filePath);
            inputStream = new FileInputStream(file);
            buffer = new BufferedInputStream(inputStream);
            readResult = new ArrayList<>();

            while (buffer.available() > 0) {
                char nextChar = (char) buffer.read();
                readStringBuilder.append(nextChar);

                if (nextChar == '\n') {
                    readStringBuilder.delete(readStringBuilder.length() - 1, readStringBuilder.length());
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

    public void writeFile() {
        try {
            Log.logger.log(Level.INFO,"Writing csv string to csv file");
            FileWriter fileWriter = new FileWriter("2.csv");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            CSVParser csvParser = new CSVParser();
            String output = csvParser.makeFileFromRoute(routeManager.getRoutes());
            printWriter.print(output);
            //TODO: перенести в finally-блок
            printWriter.close();
            fileWriter.close();

        } catch(IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        }
    }



    public boolean isWindows() {
        return System.getProperty("os.name").startsWith("Windows");
    }
}
