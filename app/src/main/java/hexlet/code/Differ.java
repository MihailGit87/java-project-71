package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(File file1, File file2) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> parsMap1 = mapper.readValue(file1, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> parsMap2 = mapper.readValue(file2, new TypeReference<Map<String, Object>>() {
        });

        Map<String, Object> commonMap = new HashMap<>();
        commonMap.putAll(parsMap1);
        commonMap.putAll(parsMap2);

        String  result;
        result = commonMap
                .keySet()
                .stream()
                .sorted()
                .map(key -> {
                    String out = "";
                    if (parsMap1.containsKey(key) && !parsMap2.containsKey(key)){
                        out = out + "-" + key + ": " + parsMap1.get(key);
                    } else if (!parsMap1.containsKey(key) && parsMap2.containsKey(key)) {
                        out = out + "+ " + key + ": " + parsMap2.get(key);
                    } else {
                        if(parsMap1.get(key).equals(parsMap2.get(key))){
                            out = out + " " + key + parsMap1.get(key);
                        } else {
                            out = out + "- " + key + ": " + parsMap1.get(key) + "\n";
                            out = out + "+ " + key + ": " + parsMap2.get(key);
                        }
                    }
                    return out;
                })
                .collect(Collectors.joining("\n"));
        System.out.println(result);
        return result;
    }
}