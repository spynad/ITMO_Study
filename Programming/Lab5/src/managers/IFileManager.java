package managers;

import java.util.ArrayList;

public interface IFileManager {
    ArrayList<String> readFile();
    void setFileName(String fileName);
    void writeFile();
}
