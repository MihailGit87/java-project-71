package hexlet.code.formatters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonTest {
    private List<Map<String, Object>> list;

    @BeforeEach
    void setUp() {
        list = List.of(Map.of("status", "added", "field", "key", "value1", 1),
                Map.of("status", "removed", "field", "key1", "value2", "value"),
                Map.of("status", "unchanged", "field", "key2", "value", "value"),
                Map.of("status", "changed", "field", "key3",
                        "value1", "value", "value2", new String[]{"a", "b"}));
    }

    @Test
    void formatText() throws IOException {
        String expected = "[{\"field\":\"key\",\"status\":\"added\",\"value1\":1},"
                + "{\"field\":\"key1\",\"status\":\"removed\",\"value2\":\"value\"},"
                + "{\"field\":\"key2\",\"status\":\"unchanged\",\"value\":\"value\"},"
                + "{\"field\":\"key3\",\"status\":\"changed\",\"value1\":\"value\",\"value2\":[\"a\",\"b\"]}]";
        assertEquals(expected, new Json().formatText(list));
    }

    @Test
    void testProblemWithList() {
        try {
            list = new LinkedList<>();
            new Json().formatText(list);
        } catch (Exception e) {
            assertEquals(IOException.class, e.getClass());
        }
    }
}
