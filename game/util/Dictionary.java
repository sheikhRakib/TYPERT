package game.util;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Dictionary {
    public static List<String> readTextFile() {
        String filePath = "props/dictionary.txt";
        List<String> words = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }
}
