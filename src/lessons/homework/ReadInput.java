package lessons.homework;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadInput {
    /**
     * Чтение файла
     * */
    static public List<String> readText() {
        try {

            //List<String> lines = Files.readAllLines(Paths.get("text.txt"), StandardCharsets.UTF_8);

            // То же самое, но через Стримы и лямбды
            List<String> lines = Files.lines(Paths.get("text.txt"), StandardCharsets.UTF_8)
                    .collect(Collectors.toList());

            return lines;
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Чтения файлов из заданной папки
     * */
    static public void readTextFile() {
        try {
            Charset charset = Charset.forName("UTF-8");
            String myDir = "./src/lessons/homework";

            File folder = new File(myDir);
            if (folder.isDirectory()) {
                for (File f : folder.listFiles()) {
                    Path myPath = Paths.get(myDir,f.getName());
                    List<String> lines = Files.readAllLines(myPath, charset);
                    for (String line : lines) {
                        System.out.println(line);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }

    }

}

