package mate.hwdao.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {
    public static String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new
                java.io.FileReader((fileName)))) {
            String newLine = bufferedReader.readLine();
            while (newLine != null) {
                stringBuilder.append(newLine.toLowerCase()).append(" ");
                newLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found", e);
        } catch (IOException e) {
            throw new RuntimeException("File was not read", e);
        }
        return stringBuilder.toString();
    }
}
