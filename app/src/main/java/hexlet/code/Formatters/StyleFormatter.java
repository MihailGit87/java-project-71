package hexlet.code.Formatters;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface StyleFormatter {
    String formatText(List<Map<String, Objects>> list) throws IOException;
}
