package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class DifferTest {

    public DifferTest() throws IOException {
    }

    public static String getPath(String pathString) {
        Path path = Path.of("src/test/resources/fixtures/" + pathString);
        if (!Files.exists(path)) {
            try {
                throw new Exception("File '" + path + "' does not exist");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return String.valueOf(path);
    }

    String expectedPlain = Files.readString(Path.of(getPath("expectedPlain.txt")));
    String expectedStylish = Files.readString(Path.of(getPath("expectedStylish.txt")));
    String expectedJson = Files.readString(Path.of(getPath("expectedJson.txt")));
    private final String FILE_1_JSON = getPath("file1.json");
    String FILE_2_JSON = getPath("file2.json");
    String FILE_1_YAML = getPath("file1.yml");
    String FILE_2_YAML = getPath("file2.yaml");

    @Test
    void testGenerateStylish() throws IOException {
        assertEquals(expectedStylish, Differ.generate(FILE_1_JSON, FILE_2_JSON));
        assertEquals(expectedStylish, Differ.generate(FILE_1_YAML, FILE_2_YAML, "stylish"));
    }

    @Test
    void testGeneratePlain() throws IOException {
        assertEquals(expectedPlain, Differ.generate(FILE_1_YAML, FILE_2_YAML, "plain"));
    }

    @Test
    void testGenerateJson() throws IOException {
        assertEquals(expectedJson, Differ.generate(FILE_1_YAML, FILE_2_YAML, "json"));
    }

    @Test
    void testGenerateWithDifferentExtension() throws IOException {
        assertEquals(expectedStylish, Differ.generate(FILE_1_JSON, FILE_2_YAML, "stylish"));
    }
}
