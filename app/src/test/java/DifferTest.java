//import hexlet.code.Differ;
//import hexlet.code.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
//import java.io.IOException;

//import static org.junit.jupiter.api.Assertions.assertEquals;

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
//    @Test
//    public void differTest() throws IOException {
//        String expected = "{\n"
//                + "    chars1: [a, b, c]\n"
//                + "  - chars2: [d, e, f]\n"
//                + "  + chars2: false\n"
//                + "  - checked: false\n"
//                + "  + checked: true\n"
//                + "  - default: null\n"
//                + "  + default: [value1, value2]\n"
//                + "  - id: 45\n"
//                + "  + id: null\n"
//                + "  - key1: value1\n"
//                + "  + key2: value2\n"
//                + "    numbers1: [1, 2, 3, 4]\n"
//                + "  - numbers2: [2, 3, 4, 5]\n"
//                + "  + numbers2: [22, 33, 44, 55]\n"
//                + "  - numbers3: [3, 4, 5]\n"
//                + "  + numbers4: [4, 5, 6]\n"
//                + "  + obj1: {nestedKey=value, isNested=true}\n"
//                + "  - setting1: Some value\n"
//                + "  + setting1: Another value\n"
//                + "  - setting2: 200\n"
//                + "  + setting2: 300\n"
//                + "  - setting3: true\n"
//                + "  + setting3: none\n"
//                + "}";
//        String actual = Differ.generate(file1.toPath(), file2.toPath());
////        assertEquals(expected, Parser.parseContent(yml, "yml"));
//        assertEquals(expected, actual);
//    }
}
