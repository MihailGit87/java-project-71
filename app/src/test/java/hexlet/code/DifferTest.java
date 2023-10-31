package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class DifferTest {
    private final String formatStylish = "stylish";
    private final String formatPlain = "plain";
    private final String formatJson = "json";

    @BeforeAll
    static void setBefore() throws IOException {
        expectedStylish = Files.readString(Path.of(PATH_TO_FIXTURES + "expectedStylish.txt"));
        expectedPlain = Files.readString(Path.of(PATH_TO_FIXTURES + "expectedPlain.txt"));
        expectedJson = Files.readString(Path.of(PATH_TO_FIXTURES + "expectedJson.txt"));
    }
    private static final String PATH_TO_FIXTURES = "src/test/resources/fixtures/";
    private static final String FILE_1_JSON = PATH_TO_FIXTURES + "file1.json";
    private static final String FILE_2_JSON = PATH_TO_FIXTURES + "file2.json";
    private static final String FILE_1_FULL_PATH = Path.of(FILE_1_JSON).toAbsolutePath().toString();
    private static final String FILE_1_YAML = PATH_TO_FIXTURES + "file1.yml";
    private static final String FILE_2_YAML = PATH_TO_FIXTURES + "file2.yaml";
    private static final String FILE_1_TXT =  PATH_TO_FIXTURES + "file1.txt";
    private static final String FILE_2_TXT =  PATH_TO_FIXTURES + "file2.txt";
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;

    @Test
    void testGenerateStylish() throws IOException {
        assertEquals(Differ.generate(FILE_1_JSON, FILE_2_JSON), expectedStylish);
        assertEquals(Differ.generate(FILE_1_FULL_PATH, FILE_2_JSON, formatStylish), expectedStylish);
        assertEquals(Differ.generate(FILE_1_YAML, FILE_2_YAML, formatStylish), expectedStylish);
    }

    @Test
    void testGeneratePlain() throws IOException {
        assertEquals(expectedPlain, Differ.generate(FILE_1_YAML, FILE_2_YAML, formatPlain));
    }

    @Test
    void testGenerateJson() throws IOException {
        assertEquals(expectedJson, Differ.generate(FILE_1_YAML, FILE_2_YAML, formatJson));
    }

    @Test
    void testGenerateWithUnknownExtension() {
        assertThrowsExactly(IOException.class, () -> {
            Differ.generate(FILE_1_TXT, FILE_2_TXT, formatStylish);
        });
        assertThrowsExactly(IOException.class, () -> {
            Differ.generate(FILE_1_TXT, FILE_2_JSON, formatStylish);
        });
        assertThrowsExactly(IOException.class, () -> {
            Differ.generate(FILE_1_JSON, FILE_2_TXT, formatStylish);
        });
    }

    @Test
    void testGenerateWithDifferentExtension() throws IOException {
        assertEquals(Differ.generate(FILE_1_JSON, FILE_2_YAML, formatStylish), expectedStylish);
    }
}
