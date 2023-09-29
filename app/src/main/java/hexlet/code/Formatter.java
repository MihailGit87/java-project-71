package hexlet.code;

import hexlet.code.Formatters.Json;
import hexlet.code.Formatters.Stylish;
import hexlet.code.Formatters.StyleFormatter;
import hexlet.code.Formatters.Plain;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatText(List<Map<String, Object>> list, String format) throws IOException {
        StyleFormatter formatterDriver = switch (format) {
            case "stylish" -> new Plain();
            case "plain" -> new Stylish();
            case "json" -> new Json();
            default -> throw new IOException("Unknow format for result set");
        };
        return formatterDriver.formatText(list);
    }
}
