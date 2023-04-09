package com.lundih.usercontact.entities;

import com.lundih.usercontact.enums.Country;
import com.lundih.usercontact.enums.Gender;
import com.lundih.usercontact.enums.Nationality;
import com.lundih.usercontact.exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.Instant;

public class UserTest {
    private final User user = new User();

    @Test
    void setId_should_set_id() {
        Long value = 0L;
        user.setId(value);
        Assertions.assertEquals(value, user.getId());
    }

    @Test
    void setFirstName_should_set_firstName() {
        String value = "firstName";
        user.setFirstName(value);
        Assertions.assertEquals(value, user.getFirstName());
    }

    @Test
    void setLastName_should_set_lastName() {
        String value = "lastName";
        user.setLastName(value);
        Assertions.assertEquals(value, user.getLastName());
    }

    @Test
    void setCountry_should_set_country() {
        Country value = Country.AUSTRALIA;
        user.setCountry(value);
        Assertions.assertEquals(value, user.getCountry());
    }

    @Test
    void setNationality_should_set_nationality() {
        Nationality value = Nationality.AUSTRALIAN;
        user.setNationality(value);
        Assertions.assertEquals(value, user.getNationality());
    }

    @Test
    void setBirthday_should_set_birthday() {
        Instant value = Instant.EPOCH;
        user.setBirthDay(value);
        Assertions.assertEquals(value, user.getBirthDay());
    }

    @Test
    void setGender_should_set_gender() {
        Gender value = Gender.MALE;
        user.setGender(value);
        Assertions.assertEquals(value, user.getGender());
    }

    @Test
    void setNationalId_should_set_nationalId() {
        String value = "nationalId";
        user.setNationalId(value);
        Assertions.assertEquals(value, user.getNationalId());
    }

    @Test
    void setCreatedOn_should_set_createdOn() {
        Instant value = Instant.EPOCH;
        user.setCreatedOn(value);
        Assertions.assertEquals(value, user.getCreatedOn());
    }

    @Test
    void setUpdatedOn_should_set_updatedOn() {
        Instant value = Instant.EPOCH;
        user.setUpdatedOn(value);
        Assertions.assertEquals(value, user.getUpdatedOn());
    }

    @Test
    void validate_should_throw_an_exception_if_birthday_is_in_the_future() {
        user.setBirthDay(Instant.now().plusMillis(84600));
        Assertions.assertThrows(InvalidInputException.class, user::validate);
    }

    @Test
    void validate_should_not_throw_an_exception_if_birthday_is_not_in_the_future() {
        user.setBirthDay(Instant.now().minusMillis(84600));
        Assertions.assertDoesNotThrow(user::validate);
    }

    @Test
    void validate_should_not_check_birthDay_if_null() {
        user.setBirthDay(null);
        Assertions.assertDoesNotThrow(user::validate);
    }
}
