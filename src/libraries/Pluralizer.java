package libraries;
import java.util.Map;

public class Pluralizer {

    public static String getTableName(Class<?> clazz) {
        String className = clazz.getSimpleName();
        return pluralize(className.toLowerCase());
    }

    private static String pluralize(String singular) {
        if (singular == null || singular.isEmpty()) {
            return singular;
        }

        // Irregular plurals
        Map<String, String> irregularPlurals = Map.of(
            "person", "people",
            "child", "children",
            "foot", "feet",
            "tooth", "teeth",
            "mouse", "mice"
        );

        // Handle irregular plural forms
        if (irregularPlurals.containsKey(singular.toLowerCase())) {
            return irregularPlurals.get(singular.toLowerCase());
        }

        // Words ending with 'y', replace 'y' with 'ies', unless preceded by a vowel
        if (singular.endsWith("y") && !isVowel(singular.charAt(singular.length() - 2))) {
            return singular.substring(0, singular.length() - 1) + "ies";
        }

        // Words ending with 's', 'x', 'z', 'ch', or 'sh', add 'es'
        if (singular.endsWith("s") || singular.endsWith("x") || singular.endsWith("z") ||
            singular.endsWith("ch") || singular.endsWith("sh")) {
            return singular + "es";
        }

        // Default case: add 's'
        return singular + "s";
    }

    private static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }
}
