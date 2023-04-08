package com.lundih.usercontact.utils;

public class Helpers {
    /**
     * Checks if values provided are null, empty or less than 0
     *
     * @param value Value to be checked
     * @return false for null, empty, or less than 0 values
     * @param <T> Generic type
     */
    public static <T> Boolean propertyValid(T value) {
        if (value == null) return false;
        else {
            if (value instanceof String) {
                return !((String) value).isEmpty();
            } else if (value instanceof Integer) {
                return ((Integer) value) >= 0;
            } else if (value instanceof Long) {
                return ((Long) value) >= 0;
            } else if (value instanceof Double) {
                return ((Double) value) >= 0;
            } else {
                return true;
            }
        }
    }
}
