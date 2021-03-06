import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

@SuppressWarnings("WeakerAccess")
public class Grouper {
    public static Map<String, List<String>> group(String words) {
        if (words == null || words.isEmpty()) return new TreeMap<>();
        return Arrays.stream(words.split(" "))
                .distinct().map(String::toLowerCase)
                .collect(groupingBy(word -> word.substring(0, 1)))
                .entrySet().stream()
                .filter(group -> group.getValue().size() > 1)
                .peek(group -> group.getValue()
                        .sort(Comparator.comparing(String::length).reversed()
                                .thenComparing(String::compareTo)))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> newValue, TreeMap::new));
    }

    public static void main(String[] args) {
        // Test for given example
        String input = "сарай сапог арбуз болт бокс биржа";
        System.out.println("Expected: [б=[биржа, бокс, болт], с=[сапог, сарай]]");
        System.out.println("Actual:   " + group(input).entrySet());
    }
}
