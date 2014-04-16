package com.github.webdriverextensions.internal.utils;

import static org.apache.commons.lang3.StringUtils.contains;

public class StringUtils {

    public static String appendNewLineIfContainsNewLine(String string) {
        if (contains(string, "\n")) {
            return "\n" + string;
        }
        return string;
    }

    public static String surroundNewLinesIfContainsNewLine(String string) {
        if (contains(string, "\n")) {
            return "\n" + string + "\n";
        }
        return string;
    }

    public static String prependSpaceIfNotBlank(String string) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(string)) {
            return " " + string;
        }
        return string;
    }

    public static String quote(String text) {
        return "\"" + text + "\"";
    }

    public static String quote(double number) {
        return "\"" + NumberUtils.toString(number) + "\"";
    }
}