package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static hexlet.code.Differ.generate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

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
    public void testWrongPath() {
        var thrown = catchThrowable(
                () -> generate("newfile1.json", "newfile2.json")
        );
        assertThat(thrown).isInstanceOf(Exception.class);
    }

    @Test
    public void testJson() throws Exception {
        assertThat(generate(file1Json, file2Json)).isEqualTo(expectedStylish);
        assertThat(generate(file1Json, file2Json, "stylish")).isEqualTo(expectedStylish);
        assertThat(generate(file1Json, file2Json, "plain")).isEqualTo(expectedPlain);
        assertThat(generate(file1Json, file2Json, "json")).isEqualTo(expectedJson);
    }

    @Test
    public void testYML() throws Exception {
        assertThat(generate(file1Yaml, file2Yaml)).isEqualTo(expectedStylish);
        assertThat(generate(file1Yaml, file2Yaml, "stylish")).isEqualTo(expectedStylish);
        assertThat(generate(file1Yaml, file2Yaml, "plain")).isEqualTo(expectedPlain);
        assertThat(generate(file1Yaml, file2Yaml, "json")).isEqualTo(expectedJson);
    }
}
