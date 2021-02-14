package managers;

import java.util.ArrayList;

public interface IFileManager {
    ArrayList<String> readFile(String filePath);
    void writeFile();
}
