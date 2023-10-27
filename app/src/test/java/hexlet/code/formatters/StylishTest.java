package hexlet.code.formatters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StylishTest {
    private List<Map<String, Object>> list;

    @BeforeEach
    void setUp() {
        List<String> str = List.of("a", "b");
        list = List.of(Map.of("status", "added", "field", "key", "value2", 1),
                Map.of("status", "removed", "field", "key1", "value1", "value"),
                Map.of("status", "unchanged", "field", "key2", "value", "value"),
                Map.of("status", "changed", "field", "key3",
                        "value1", "value", "value2", str));
    }

    @Test
    void testFormatText() {
        String expected = """
                {
                  + key: 1
                  - key1: value
                    key2: value
                  - key3: value
                  + key3: [a, b]
                }""";

        assertEquals(expected, new Stylish().formatText(list));
    }

    @Test
    void testBadDiffStatus() {
        try {
            list = List.of(Map.of("status", "added1", "field", "key", "value1", 1));
            new Stylish().formatText(list);
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
        }
    }
}
