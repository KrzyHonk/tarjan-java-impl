package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;

public class FileReader {
    public InputStream getFileInputStreamFromFile(Path filepath) {
        try {
            return new FileInputStream(filepath.toFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
