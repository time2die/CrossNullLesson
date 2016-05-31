package lessons.homework;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class ReadInput {
    static public List<String> readText() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("text.txt"), StandardCharsets.UTF_8);
            return lines;
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}

