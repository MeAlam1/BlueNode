// Copyright (c) BlueNode. Licensed under the MIT License.

package com.mealam.bluenode.utils.conversion;

import com.mealam.bluenode.utils.logging.BaseLogLevel;
import com.mealam.bluenode.utils.logging.BaseLogger;

@SuppressWarnings("unused")
public class CaseUtils {

    private CaseUtils() {}

    private static String handleNullOrEmptyInput(String pInput, String pMethodName) {
        if (pInput == null || pInput.isEmpty()) {
            BaseLogger.log(BaseLogLevel.WARNING, pMethodName + " input is null or empty.");
            return pInput;
        }
        return pInput;
    }

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

    public static String toSnakeCase(String pInput) {
        pInput = handleNullOrEmptyInput(pInput, "toSnakeCase");
        String result = pInput.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
        return result.replace("-", "_");
    }

    public static String toKebabCase(String pInput) {
        pInput = handleNullOrEmptyInput(pInput, "toKebabCase");
        String result = pInput.replaceAll("([a-z])([A-Z])", "$1-$2").toLowerCase();
        return result.replace("_", "-");
    }

    public static String toUpperSnakeCase(String pInput) {
        pInput = handleNullOrEmptyInput(pInput, "toUpperSnakeCase");
        return toSnakeCase(pInput).toUpperCase();
    }

    public static String toTrainCase(String pInput) {
        pInput = handleNullOrEmptyInput(pInput, "toTrainCase");
        String result = toKebabCase(pInput).replace("-", " ");
        return toCamelCase(result).replace(" ", "-");
    }

    public static String toFlatcase(String pInput) {
        pInput = handleNullOrEmptyInput(pInput, "toFlatcase");
        return pInput.replaceAll("[_-]", "").toLowerCase();
    }

    public static String toCobolCase(String pInput) {
        pInput = handleNullOrEmptyInput(pInput, "toCobolCase");
        return toKebabCase(pInput).toUpperCase();
    }

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
