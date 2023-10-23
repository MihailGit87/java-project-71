package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Stylish implements StyleFormatter {
    private final String patternAdded = "  + %s: %s";
    private final String patternRemoved = "  - %s: %s";
    private final String patternChanged = "  - %s: %s\n  + %s: %s";
    private final String patternUnchanged = "    %s: %s";

    @Override
    public String formatText(List<Map<String, Object>> list) {
        return list.stream()
                .map(line -> {
                    Object status = Optional.ofNullable(line.get("status")).orElse("null");
                    Object field = Optional.ofNullable(line.get("field")).orElse("null");

                    if (status.equals("added")) {
                        return patternAdded.formatted(field, line.get("value1"));
                    } else if (status.equals("removed")) {
                        return patternRemoved.formatted(field, line.get("value2"));
                    } else if (status.equals("changed")) {
                        return patternChanged.formatted(field, line.get("value1"),
                                field, line.get("value2"));
                    } else if (status.equals("unchanged")) {
                        return patternUnchanged.formatted(field, line.get("value"));
                    } else {
                        throw new RuntimeException("Unknown status for diff");
                    }
                }).collect(Collectors.joining("\n", "{\n", "\n}"));
    }
}
