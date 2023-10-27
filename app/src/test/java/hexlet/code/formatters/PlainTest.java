package hexlet.code.formatters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlainTest {
    private List<Map<String, Object>> list;

    @BeforeEach
    void setUp() {
        list = List.of(Map.of("status", "added", "field", "key", "value2", 1),
                Map.of("status", "removed", "field", "key1", "value1", "value"),
                Map.of("status", "unchanged", "field", "key2", "value", "value"),
                Map.of("status", "changed", "field", "key3",
                        "value2", "value", "value1", new String[]{"a", "b"}));
    }

    @Test
    void testFormatText() {
        String expected = """
                Property 'key' was added with value: 1
                Property 'key1' was removed
                Property 'key3' was updated. From [complex value] to 'value'""";

        assertEquals(expected, new Plain().formatText(list));
    }

    @Test
    void testUnknownFormatText() {
        list = List.of(Map.of("status", "added1", "field", "key", "value1", 1));
        try {
            new Plain().formatText(list);
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
        }
    }
}
