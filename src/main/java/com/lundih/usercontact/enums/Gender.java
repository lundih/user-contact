package com.lundih.usercontact.enums;

/**
 * This class standardises genders
 * <p>
 * The class ensures that a gender provided from the user input is one of the expected values.
 * This reduces the chances that a typo will appear in the gender.
 *
 * @author lundih
 * @since 0.0.1
 */
public enum Gender {
    FEMALE("Female"),
    MALE("Male"),
    OTHER("Other"),
    PREFER_NOT_TO_SAY("Prefer not to say"),
    UNKNOWN("Unknown");

    private final String value;

    Gender(String gender) {
        value = gender;
    }

    @Override
    public String toString() {
        return value;
    }
}
