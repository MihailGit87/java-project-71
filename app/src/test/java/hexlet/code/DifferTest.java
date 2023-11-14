package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static String getPath(String fileName) {
        return String.valueOf(Paths.get("src/test/resources/fixtures", fileName).toAbsolutePath().normalize());
    }

    private static String expectedPlain;
    private static String expectedStylish;
    private static String expectedJson;
    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setBefore() throws IOException {
        expectedPlain = Files.readString(Path.of(getPath("expectedPlain.txt")));
        expectedStylish = Files.readString(Path.of(getPath("expectedStylish.txt")));
        expectedJson = Files.readString(Path.of(getPath("expectedJson.txt")));
        objectMapper = new ObjectMapper();
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public final void generateTest(String format) throws Exception {
        String filePath1 = getPath("file1." + format);
        String filePath2 = getPath("file2." + format);

        assertThat(generate(filePath1, filePath2)).isEqualTo(expectedStylish);
        assertThat(generate(filePath1, filePath2, "stylish")).isEqualTo(expectedStylish);
        assertThat(generate(filePath1, filePath2, "plain")).isEqualTo(expectedPlain);
        assertEquals(objectMapper.readTree(generate(filePath1, filePath2, "json")),
                objectMapper.readTree(expectedJson));
    }

    @Test
    public void testWrongPath() {
        var thrown = catchThrowable(
                () -> generate("newfile1.json", "newfile2.json")
        );
        assertThat(thrown).isInstanceOf(Exception.class);
    }
}
