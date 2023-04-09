package com.lundih.usercontact.utils;

import com.lundih.usercontact.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.lundih.usercontact.utils.Helpers.propertyValid;

public class HelpersTest {
    @Test
    void propertyValid_should_return_false_when_value_is_null() {
        Assertions.assertFalse(propertyValid(null));
    }

    @Test
    void propertyValid_should_return_false_when_value_is_empty_string() {
        Assertions.assertFalse(propertyValid(""));
    }

    @Test
    void propertyValid_should_return_false_when_value_is_less_than_zero() {
        Assertions.assertFalse(propertyValid(-1));
        Assertions.assertFalse(propertyValid(-1L));
        Assertions.assertFalse(propertyValid(-0.1));
    }

    @Test
    void propertyValid_should_return_true_when_value_is_valid() {
        Assertions.assertTrue(propertyValid("validProperty"));
        Assertions.assertTrue(propertyValid(1));
        Assertions.assertTrue(propertyValid(1L));
        Assertions.assertTrue(propertyValid(0.1));
        Assertions.assertTrue(propertyValid(new User()));
    }
}
