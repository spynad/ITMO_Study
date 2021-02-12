package managers;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;

import csv.CSVReader;
import log.Log;
import route.Route;

public class FileManager {
    private File file;
    private FileInputStream inputStream;
    private BufferedInputStream buffer;
    private StringBuilder readStringBuilder;
    private ArrayList<String> readResult;

    public FileManager() {
        Log.logger.log(Level.INFO,"FileManager init");
    }

    public ArrayList<String> readFile(String filePath) {
        Log.logger.log(Level.INFO,"Reading file " + filePath);
        try {
            CSVReader csvReader = new CSVReader();
            readStringBuilder = new StringBuilder();
            file = new File(filePath);
            inputStream = new FileInputStream(file);
            buffer = new BufferedInputStream(inputStream);
            readResult = new ArrayList<>();

            while (buffer.available() > 0) {
                char nextChar = (char) buffer.read();
                readStringBuilder.append(nextChar);

                if (nextChar == '\n') {
                    readStringBuilder.delete(readStringBuilder.length() - 2, readStringBuilder.length());
                    readResult.add(readStringBuilder.toString());
                    readStringBuilder.delete(0, readStringBuilder.length());
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("The file not found: " + fnfe);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (inputStream != null && buffer != null) {
                    buffer.close();
                    inputStream.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return readResult;
    }

    public void writeFile(Stack<Route> routes) {

    }



    public boolean isWindows() {
        return System.getProperty("os.name").startsWith("Windows");
    }
}
