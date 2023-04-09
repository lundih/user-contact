package com.lundih.usercontact.dtos.requests;

import com.lundih.usercontact.enums.Country;
import com.lundih.usercontact.enums.Gender;
import com.lundih.usercontact.enums.Nationality;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class UserRequestTest {
    private final String firstName = "firstName";
    private final String lastName = "lastName";
    private final Country country = Country.ANGOLA;
    private final Nationality nationality = Nationality.ANGOLAN;
    private final Instant birthDay = Instant.EPOCH;
    private final Gender gender = Gender.FEMALE;
    private final String nationalId = "nationalId";

    private final UserRequest request = new UserRequest(firstName, lastName, country, nationality, birthDay, gender,
            nationalId);

    @Test
    void constructor_should_not_accept_null_firstName() {
        Assertions.assertThrows(Exception.class,
                () -> new UserRequest(null, lastName, country, nationality, birthDay, gender, nationalId));
    }

    @Test
    void constructor_should_not_accept_null_lastName() {
        Assertions.assertThrows(Exception.class,
                () -> new UserRequest(firstName, null, country, nationality, birthDay, gender, nationalId));
    }

    @Test
    void constructor_should_not_accept_null_country() {
        Assertions.assertThrows(Exception.class,
                () -> new UserRequest(firstName, lastName, null, nationality, birthDay, gender, nationalId));
    }
    @Test
    void constructor_should_not_accept_null_nationality() {
        Assertions.assertThrows(Exception.class,
                () -> new UserRequest(firstName, lastName, country, null, birthDay, gender, nationalId));
    }
    @Test
    void constructor_should_not_accept_null_birthDay() {
        Assertions.assertThrows(Exception.class,
                () -> new UserRequest(firstName, lastName, country, nationality, null, gender, nationalId));
    }
    @Test
    void constructor_should_not_accept_null_gender() {
        Assertions.assertThrows(Exception.class,
                () -> new UserRequest(firstName, lastName, country, nationality, birthDay, null, nationalId));
    }
    @Test
    void constructor_should_not_accept_null_nationalId() {
        Assertions.assertThrows(Exception.class,
                () -> new UserRequest(firstName, lastName, country, nationality, birthDay, gender, null));
    }

    @Test
    void getFirstName_should_get_firstName() {
        Assertions.assertEquals(firstName, request.getFirstName());
    }

    @Test
    void getLastName_should_get_lastName() {
        Assertions.assertEquals(lastName, request.getLastName());
    }

    @Test
    void getCountry_should_get_country() {
        Assertions.assertEquals(country, request.getCountry());
    }

    @Test
    void getNationality_should_get_nationality() {
        Assertions.assertEquals(nationality, request.getNationality());
    }

    @Test
    void getBirthday_should_get_birthday() {
        Assertions.assertEquals(birthDay, request.getBirthDay());
    }

    @Test
    void getGender_should_get_gender() {
        Assertions.assertEquals(gender, request.getGender());
    }

    @Test
    void getNationalId_should_get_nationalId() {
        Assertions.assertEquals(nationalId, request.getNationalId());
    }
}