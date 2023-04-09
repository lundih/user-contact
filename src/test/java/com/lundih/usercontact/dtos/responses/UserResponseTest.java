package com.lundih.usercontact.dtos.responses;

import com.lundih.usercontact.enums.Country;
import com.lundih.usercontact.enums.Gender;
import com.lundih.usercontact.enums.Nationality;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class UserResponseTest {
    private final UserResponse response = new UserResponse();

    @Test
    void setId_should_set_id() {
        Long value = 0L;
        response.setId(value);
        Assertions.assertEquals(value, response.getId());
    }

    @Test
    void setFirstName_should_set_firstName() {
        String value = "firstName";
        response.setFirstName(value);
        Assertions.assertEquals(value, response.getFirstName());
    }

    @Test
    void setLastName_should_set_lastName() {
        String value = "lastName";
        response.setLastName(value);
        Assertions.assertEquals(value, response.getLastName());
    }

    @Test
    void setCountry_should_set_country() {
        String value = Country.AUSTRALIA.toString();
        response.setCountry(value);
        Assertions.assertEquals(value, response.getCountry());
    }

    @Test
    void setNationality_should_set_nationality() {
        String value = Nationality.AUSTRALIAN.toString();
        response.setNationality(value);
        Assertions.assertEquals(value, response.getNationality());
    }

    @Test
    void setBirthday_should_set_birthday() {
        Instant value = Instant.EPOCH;
        response.setBirthDay(value);
        Assertions.assertEquals(value, response.getBirthDay());
    }

    @Test
    void setGender_should_set_gender() {
        String value = Gender.MALE.toString();
        response.setGender(value);
        Assertions.assertEquals(value, response.getGender());
    }

    @Test
    void setNationalId_should_set_nationalId() {
        String value = "nationalId";
        response.setNationalId(value);
        Assertions.assertEquals(value, response.getNationalId());
    }

    @Test
    void setCreatedOn_should_set_createdOn() {
        Instant value = Instant.EPOCH;
        response.setCreatedOn(value);
        Assertions.assertEquals(value, response.getCreatedOn());
    }

    @Test
    void setUpdatedOn_should_set_updatedOn() {
        Instant value = Instant.EPOCH;
        response.setUpdatedOn(value);
        Assertions.assertEquals(value, response.getUpdatedOn());
    }
}
