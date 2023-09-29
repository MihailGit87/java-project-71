import hexlet.code.Differ;
//import hexlet.code.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void testDiffer() {
        Differ dif = new Differ();
        assertEquals(Differ.class, dif.getClass());
    }
}
