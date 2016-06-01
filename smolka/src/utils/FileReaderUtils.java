package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;

public class FileReaderUtils {
    public static final FileInputStream getFileInputStreamFromFile(Path filepath) {
        try {
            return new FileInputStream(filepath.toFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
