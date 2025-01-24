// Copyright (c) BlueNode. Licensed under the MIT License.

package com.mealam.bluenode.utils.conversion;

import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;

/**
 * A utility class for converting strings between various naming conventions.
 * <p>
 * Key Methods:
 * <ul>
 * <li>{@link #toCamelCase(String)} - Converts input to camelCase.</li>
 * <li>{@link #toPascalCase(String)} - Converts input to PascalCase.</li>
 * <li>{@link #toSnakeCase(String)} - Converts input to snake_case.</li>
 * <li>{@link #toKebabCase(String)} - Converts input to kebab-case.</li>
 * <li>{@link #toUpperSnakeCase(String)} - Converts input to UPPER_SNAKE_CASE.</li>
 * <li>{@link #toTrainCase(String)} - Converts input to Train-Case.</li>
 * <li>{@link #toFlatcase(String)} - Converts input to flatcase.</li>
 * <li>{@link #toCobolCase(String)} - Converts input to COBOL-CASE.</li>
 * </ul>
 *
 * @author MeAlam
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class CaseUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private CaseUtils() {}

    /**
     * Common method to handle null or empty input.
     * Logs a warning if input is null or empty.
     */
    private static String handleNullOrEmptyInput(String pInput, String methodName) {
        if (pInput == null || pInput.isEmpty()) {
            BaseLogger.log(BaseLogLevel.WARNING, methodName + " input is null or empty.");
            return pInput;
        }
        return pInput;
    }

    /**
     * Converts input to camelCase.
     */
    public static String toCamelCase(String pInput) {
        pInput = handleNullOrEmptyInput(pInput, "toCamelCase");
        if (pInput.contains("_")) {
            return convertUsingDelimiter(pInput, "_", true);
        }
        if (pInput.contains("-")) {
            return convertUsingDelimiter(pInput, "-", true);
        }
        if (Character.isUpperCase(pInput.charAt(0)) && !pInput.contains("_") && !pInput.contains("-")) {
            return pInput.substring(0, 1).toLowerCase() + pInput.substring(1);
        }
        return pInput;
    }

    /**
     * Converts input to PascalCase.
     */
    public static String toPascalCase(String pInput) {
        pInput = handleNullOrEmptyInput(pInput, "toPascalCase");
        if (pInput.contains("_")) {
            return convertUsingDelimiter(pInput, "_", false);
        }
        if (pInput.contains("-")) {
            return convertUsingDelimiter(pInput, "-", false);
        }
        if (Character.isLowerCase(pInput.charAt(0))) {
            return pInput.substring(0, 1).toUpperCase() + pInput.substring(1);
        }
        return pInput;
    }

    /**
     * Converts input to snake_case.
     */
    public static String toSnakeCase(String pInput) {
        pInput = handleNullOrEmptyInput(pInput, "toSnakeCase");
        String result = pInput.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
        return result.replace("-", "_");
    }

    /**
     * Converts input to kebab-case.
     */
    public static String toKebabCase(String pInput) {
        pInput = handleNullOrEmptyInput(pInput, "toKebabCase");
        String result = pInput.replaceAll("([a-z])([A-Z])", "$1-$2").toLowerCase();
        return result.replace("_", "-");
    }

    /**
     * Converts input to UPPER_SNAKE_CASE.
     */
    public static String toUpperSnakeCase(String pInput) {
        pInput = handleNullOrEmptyInput(pInput, "toUpperSnakeCase");
        return toSnakeCase(pInput).toUpperCase();
    }

    /**
     * Converts input to Train-Case.
     */
    public static String toTrainCase(String pInput) {
        pInput = handleNullOrEmptyInput(pInput, "toTrainCase");
        String result = toKebabCase(pInput).replace("-", " ");
        return toCamelCase(result).replace(" ", "-");
    }

    /**
     * Converts input to flatcase.
     */
    public static String toFlatcase(String pInput) {
        pInput = handleNullOrEmptyInput(pInput, "toFlatcase");
        return pInput.replaceAll("[_-]", "").toLowerCase();
    }

    /**
     * Converts input to COBOL-CASE.
     */
    public static String toCobolCase(String pInput) {
        pInput = handleNullOrEmptyInput(pInput, "toCobolCase");
        return toKebabCase(pInput).toUpperCase();
    }

    /**
     * Helper method to convert strings using a specified delimiter.
     */
    private static String convertUsingDelimiter(String pInput, String pDelim, boolean pCamel) {
        String[] parts = pInput.split(pDelim);
        StringBuilder sb = new StringBuilder();

        for (String part : parts) {
            if (pCamel && sb.isEmpty()) {
                sb.append(part.substring(0, 1).toLowerCase());
            } else {
                sb.append(part.substring(0, 1).toUpperCase());
            }
            sb.append(part.substring(1).toLowerCase());
        }

        return sb.toString();
    }
}
