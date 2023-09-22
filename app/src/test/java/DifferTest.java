import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class DifferTest {
    File file1;
    File file2;
    @BeforeEach
    public void setup() {
        file1 = new File("/home/mikhailvasilev/java-project-71/app/src/test/resources/filepath1.json");
        file2 = new File("/home/mikhailvasilev/java-project-71/app/src/test/resources/filepath2.json");
    }
    @Test
    public void existFile() {
        Assertions.assertTrue(file1.exists());
        Assertions.assertTrue(file2.exists());
    }
    @Test
    public void differTest() throws IOException {
        String expected = "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true";
        String actual = Differ.generate(file1.toPath(), file2.toPath());
    }
}
