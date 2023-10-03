package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class DifferTest {
    private static String file1json;
    private static String file2json;
    private static String file1FullPath;
    private static String file1yaml;
    private static String file2yaml;
    private static String file1txt;
    private static String file2txt;
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;
    private static final String PATH_TO_FIXTURES = "src/test/resources/";

    @BeforeAll
    static void setBefore() throws IOException {
        expectedStylish = Files.readString(Path.of(PATH_TO_FIXTURES + "fixtures/expectedStylish.txt"));
        expectedPlain = Files.readString(Path.of(PATH_TO_FIXTURES + "fixtures/expectedPlain.txt"));
        expectedJson = Files.readString(Path.of(PATH_TO_FIXTURES + "fixtures/expectedJson.txt"));
        file1json = PATH_TO_FIXTURES + "fixtures/file1.json";
        file2json = PATH_TO_FIXTURES + "fixtures/file2.json";
        file1yaml = PATH_TO_FIXTURES + "fixtures/file1.yml";
        file2yaml = PATH_TO_FIXTURES + "fixtures/file2.yaml";
        file1txt = PATH_TO_FIXTURES + "fixtures/file1.txt";
        file2txt = PATH_TO_FIXTURES + "fixtures/file2.txt";
        file1FullPath = Path.of(file1json).toAbsolutePath().toString();
    }

//    @Test
//    void testGenerateStylish() throws IOException {
//        assertEquals(Differ.generate(file1json, file2json), expectedStylish);
//        assertEquals(Differ.generate(file1FullPath, file2json, "stylish"), expectedStylish);
//        assertEquals(Differ.generate(file1yaml, file2yaml, "stylish"), expectedStylish);
//    }
//
//    @Test
//    void testGeneratePlain() throws IOException {
//        assertEquals(expectedPlain, Differ.generate(file1yaml, file2yaml, "plain"));
//    }
//
//    @Test
//    void testGenerateJson() throws IOException {
//        assertEquals(expectedJson, Differ.generate(file1yaml, file2yaml, "json"));
//    }

    @Test
    void testGenerateWithUnknownExtension() {
        assertThrowsExactly(IOException.class, () -> {
            Differ.generate(file1txt, file2txt, "stylish");
        });
        assertThrowsExactly(IOException.class, () -> {
            Differ.generate(file1txt, file2json, "stylish");
        });
        assertThrowsExactly(IOException.class, () -> {
            Differ.generate(file1json, file2txt, "stylish");
        });
    }

//    @Test
//    void testGenerateWithDifferentExtension() throws IOException {
//        assertEquals(expectedStylish, Differ.generate(file1json, file2yaml, "stylish"));
//    }

    @Test
    void testDiffer() {
        Differ dif = new Differ();
        assertEquals(Differ.class, dif.getClass());
    }
}
