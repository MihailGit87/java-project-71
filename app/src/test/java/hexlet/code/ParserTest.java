package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private String yml;

    @Test
    void testBadFormat() {
        try {
            Parser.parseContent(yml, "yml1");
        } catch (Exception e) {
            assertEquals(IOException.class, e.getClass());
        }
    }
}
