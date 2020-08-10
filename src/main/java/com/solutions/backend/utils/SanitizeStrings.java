package com.solutions.backend.utils;

public interface SanitizeStrings {

    static String[] sanitizeDoubleQuotes(String[] parts) {
        String[] sanitizedParts = parts;
        for (int i =0; i < parts.length; i++) {
            sanitizedParts[i] = parts[i].replace("\"", "");  // remove double quotes
        }
        return sanitizedParts;
    }
}
