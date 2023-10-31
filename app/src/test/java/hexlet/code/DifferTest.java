package hexlet.code;

//import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import java.io.ByteArrayOutputStream;
import java.io.IOException;
//import java.io.PrintStream;
//import java.nio.charset.StandardCharsets;
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
//        String expectedError = Files.readString(Path.of(PATH_TO_FIXTURES + "appTestExpectedError.txt"));
    }
    private static final String PATH_TO_FIXTURES = "src/test/resources/fixtures/";
    private static final String file1json = PATH_TO_FIXTURES + "file1.json";
    private static final String file2json = PATH_TO_FIXTURES + "file2.json";
    private static final String file1FullPath = Path.of(file1json).toAbsolutePath().toString();
    private static final String file1yaml = PATH_TO_FIXTURES + "file1.yml";
    private static final String file2yaml = PATH_TO_FIXTURES + "file2.yaml";
    private static final String file1txt =  PATH_TO_FIXTURES + "file1.txt";
    private static final String file2txt =  PATH_TO_FIXTURES + "file2.txt";
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;
//    private static String wrongPath = PATH_TO_FIXTURES + "sqwezsxf/qweasf.json";
//    @BeforeEach
//    final void setUp() {
//        System.setOut(new PrintStream(output));
//    }
//    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
//    private final PrintStream standardOut = System.out;


    @Test
    void testGenerateStylish() throws IOException {
        assertEquals(Differ.generate(file1json, file2json), expectedStylish);
        assertEquals(Differ.generate(file1FullPath, file2json, formatStylish), expectedStylish);
        assertEquals(Differ.generate(file1yaml, file2yaml, formatStylish), expectedStylish);
    }

    @Test
    void testGeneratePlain() throws IOException {
        assertEquals(expectedPlain, Differ.generate(file1yaml, file2yaml, formatPlain));
    }

    @Test
    void testGenerateJson() throws IOException {
        assertEquals(expectedJson, Differ.generate(file1yaml, file2yaml, formatJson));
    }

    @Test
    void testGenerateWithUnknownExtension() {
        assertThrowsExactly(IOException.class, () -> {
            Differ.generate(file1txt, file2txt, formatStylish);
        });
        assertThrowsExactly(IOException.class, () -> {
            Differ.generate(file1txt, file2json, formatStylish);
        });
        assertThrowsExactly(IOException.class, () -> {
            Differ.generate(file1json, file2txt, formatStylish);
        });
    }

    @Test
    void testGenerateWithDifferentExtension() throws IOException {
        assertEquals(Differ.generate(file1json, file2yaml, formatStylish), expectedStylish);
    }

//    @Test
//    void testDiffer() {
//        Differ dif = new Differ();
//        assertEquals(Differ.class, dif.getClass());
//    }
//
//    @Test
//    void mainWithWrongJsonFile1() {
//        App.main(wrongPath, file2json);
//        assertEquals(expectedError.formatted(wrongPath), output.toString(StandardCharsets.UTF_8).trim());
//    }
//    @Test
//    void mainJson() {
//        App.main(file1json, file2json);
//        assertEquals(output.toString(StandardCharsets.UTF_8).trim(), expectedStylish);
//    }
//    @AfterEach
//    final void tearDown() {
//        System.setOut(standardOut);
//    }
}
