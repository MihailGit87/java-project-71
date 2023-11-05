package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Plain implements StyleFormatter {
    private final String patternAdded = "Property '%s' was added with value: %s";
    private final String patternRemoved = "Property '%s' was removed";
    private final String patternChanged = "Property '%s' was updated. From %s to %s";
    private final String patternUnchanged = "";

    private String formatValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Integer || value instanceof Boolean) {
            return value.toString();
        } else if (value instanceof String) {
            return "'%s'".formatted(value);
        } else {
            return "[complex value]";
        }
    }

    @Override
    public String formatText(List<Map<String, Object>> list) {
        return list.stream()
                .map(line -> {
                    Object status = line.get("status");
                    Object field = line.get("field");

                    if (status.equals("added")) {
                        return patternAdded.formatted(field, formatValue(line.get("value2")));
                    } else if (status.equals("removed")) {
                        return patternRemoved.formatted(field);
                    } else if (status.equals("changed")) {
                        return patternChanged.formatted(field, formatValue(line.get("value1")),
                                formatValue(line.get("value2")));
                    } else if (status.equals("unchanged")) {
                        return patternUnchanged;
                    } else {
                        throw new RuntimeException("Unknown status for diff");
                    }
                })
                .filter(x -> !x.isEmpty())
                .collect(Collectors.joining("\n"));
    }
}
