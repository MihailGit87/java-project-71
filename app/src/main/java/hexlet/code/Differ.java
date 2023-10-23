package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

import org.apache.commons.io.FilenameUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Differ {

    public static String generate(String pathToFirstFile, String pathToSecondFile, String format) throws IOException {
        Map<String, Object> firstData = getData(pathToFirstFile);
        Map<String, Object> secondData = getData(pathToSecondFile);

        List<Map<String, Object>> list = Tree.build(firstData, secondData);
        return Formatter.formatText(list, format);
    }

    public static String generate(String pathToFirstFile, String pathToSecondFile) throws IOException {
        return generate(pathToFirstFile, pathToSecondFile, "stylish");
    }

    private static Map<String, Object> getData(String filePath) throws IOException {
        String content = Optional.ofNullable(Files.readString(Path.of(filePath))).orElse("null");
        String extension = Optional.ofNullable(FilenameUtils.getExtension(filePath)).orElse("null");
        return Parser.parseContent(content, extension);
    }
}
