package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.nio.file.Files;
import java.nio.file.Path;
import static hexlet.code.Differ.generate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class DifferTest {

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

    @Test
    public void testWrongPath() {
        var thrown = catchThrowable(
                () -> generate("newfile1.json", "newfile2.json")
        );
        assertThat(thrown).isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getPath("file1." + format);
        String filePath2 = getPath("file2." + format);
        String expectedPlain = Files.readString(Path.of(getPath("expectedPlain.txt")));
        String expectedStylish = Files.readString(Path.of(getPath("expectedStylish.txt")));
        String expectedJson = Files.readString(Path.of(getPath("expectedJson.txt")));

        assertThat(generate(filePath1, filePath2)).isEqualTo(expectedStylish);
        assertThat(generate(filePath1, filePath2, "stylish")).isEqualTo(expectedStylish);
        assertThat(generate(filePath1, filePath2, "plain")).isEqualTo(expectedPlain);
        assertThat(generate(filePath1, filePath2, "json")).isEqualTo(expectedJson);
    }
}
