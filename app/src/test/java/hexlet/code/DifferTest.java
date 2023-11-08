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

    private final String expectedPlain = Files.readString(Path.of(getPath("expectedPlain.txt")));
    private final String expectedStylish = Files.readString(Path.of(getPath("expectedStylish.txt")));
    private final String expectedJson = Files.readString(Path.of(getPath("expectedJson.txt")));
    private final String file1Json = getPath("file1.json");
    private final String file2Json = getPath("file2.json");
    private final  String file1Yaml = getPath("file1.yml");
    private final  String file2Yaml = getPath("file2.yaml");

    @Test
    void testGenerateStylish() throws IOException {
        assertEquals(expectedStylish, Differ.generate(file1Json, file2Json));
        assertEquals(expectedStylish, Differ.generate(file1Yaml, file2Yaml, "stylish"));
    }

    @Test
    void testGeneratePlain() throws IOException {
        assertEquals(expectedPlain, Differ.generate(file1Yaml, file2Yaml, "plain"));
    }

    @Test
    void testGenerateJson() throws IOException {
        assertEquals(expectedJson, Differ.generate(file1Yaml, file2Yaml, "json"));
    }

    @Test
    void testGenerateWithDifferentExtension() throws IOException {
        assertEquals(expectedStylish, Differ.generate(file1Json, file2Yaml, "stylish"));
    }
}
