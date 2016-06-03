package lessons.homework;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadInput {
    static public List<String> readText() {
//        try {
//            List<String> lines = Files.readAllLines(Paths.get("text.txt"), StandardCharsets.UTF_8);
//           return lines ;
//        } catch (IOException e) {
//            return Collections.emptyList();
//        }
        Stream<Path> pathStream = Stream.empty();
        try {
            pathStream = Files.find(new File("").toPath(), Integer.MAX_VALUE,
                    (filePath, fileAttr) -> fileAttr.isRegularFile() && filePath.toString().matches(".*.java"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathStream.map(path -> {
            String[] patha = path.toString().split("\\\\");
            return patha[patha.length-1];
        }).collect(Collectors.toList());
    }
}

