package libraries;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Validator {

    private final Map<String, String> errors = new HashMap<>();

    public Validator() {}

    public Map<String, String> validate(Map<String, String> inputs, Map<String, String> rules) {
        errors.clear();

        for (Map.Entry<String, String> entry : inputs.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();
            String ruleSet = rules.get(field);

            if (ruleSet != null) {
                validateField(field, value, ruleSet);
            }
        }

        return errors;
    }

    private void validateField(String field, String value, String ruleSet) {
        String[] rules = ruleSet.split("\\|");

        for (String rule : rules) {
            if (rule.equals("required") && (value == null || value.trim().isEmpty())) {
                addError(field, capitalize(field) + " is required.");
            } else if (rule.startsWith("min:")) {
                int minLength = Integer.parseInt(rule.split(":")[1]);
                if (value.length() < minLength) {
                    addError(field, capitalize(field) + " must be at least " + minLength + " characters.");
                }
            } else if (rule.startsWith("max:")) {
                int maxLength = Integer.parseInt(rule.split(":")[1]);
                if (value.length() > maxLength) {
                    addError(field, capitalize(field) + " must be no more than " + maxLength + " characters.");
                }
            } else if (rule.equals("numeric") && !isNumeric(value)) {
                addError(field, capitalize(field) + " must be a number.");
            } else if (rule.equals("email") && !isValidEmail(value)) {
                addError(field, capitalize(field) + " must be a valid email address.");
            } else if (rule.equals("phone") && !isValidPhone(value)) {
                addError(field, capitalize(field) + " must be a valid phone number.");
            } else if (rule.startsWith("unique:")) {
                String[] parts = rule.split(":")[1].split(",");
                String tableName = parts[0];
                String column = parts[1];
                String id = parts.length > 2 ? parts[2] : null;

                if (isDuplicateInTable(tableName, column, value, id)) {
                    addError(field, capitalize(field) + " must be unique.");
                }
            }
        }
    }

    private boolean isNumeric(String value) {
        return value.matches("[+-]?\\d+(\\.\\d+)?");
    }

    private boolean isValidEmail(String value) {
        return Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$").matcher(value).matches();
    }

    private boolean isValidPhone(String value) {
        // Phone number must start with '09' and be exactly 11 digits
        return Pattern.compile("^09\\d{9}$").matcher(value).matches();
    }

    private boolean isDuplicateInTable(String tableName, String column, String value, String id) {
        return SystemModel.isExist(column, value, tableName, id);
    }

    private void addError(String field, String message) {
        if (!errors.containsKey(field)) {
            errors.put(field, message);
        }
    }

    private String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
