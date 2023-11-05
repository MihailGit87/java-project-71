package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Tree {
    public static List<Map<String, Object>> build(Map<String, Object> firstData, Map<String, Object> secondData) {

        List<Map<String, Object>> result = new ArrayList<>();
        Set<Object> keys = new TreeSet<>(firstData.keySet());
        keys.addAll(secondData.keySet());

        for (Object key: keys) {
            Map<String, Object> diff = new LinkedHashMap<>();

            if (!firstData.containsKey(key)) {
                diff.put("status", "added");
                diff.put("field", key);
                diff.put("value2", secondData.get(key));
            } else if (!secondData.containsKey(key)) {
                diff.put("status", "removed");
                diff.put("field", key);
                diff.put("value1", firstData.get(key));
            } else if (Objects.equals(firstData.get(key), secondData.get(key))) {
                diff.put("status", "unchanged");
                diff.put("field", key);
                diff.put("value", firstData.get(key));
            } else {
                diff.put("status", "changed");
                diff.put("field", key);
                diff.put("value2", secondData.get(key));
                diff.put("value1", firstData.get(key));
            }
            result.add(diff);
        }
        return result;
    }
}
