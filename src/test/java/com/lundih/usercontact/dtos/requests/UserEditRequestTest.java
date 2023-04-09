package com.lundih.usercontact.dtos.requests;

import com.lundih.usercontact.enums.Country;
import com.lundih.usercontact.enums.Gender;
import com.lundih.usercontact.enums.Nationality;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class UserEditRequestTest {
    private final UserEditRequest request = new UserEditRequest();

    @Test
    void setFirstName_should_set_firstName() {
        String value = "firstName";
        request.setFirstName(value);
        Assertions.assertEquals(value, request.getFirstName());
    }

    @Test
    void setLastName_should_set_lastName() {
        String value = "lastName";
        request.setLastName(value);
        Assertions.assertEquals(value, request.getLastName());
    }

    @Test
    void setCountry_should_set_country() {
        Country value = Country.AUSTRALIA;
        request.setCountry(value);
        Assertions.assertEquals(value, request.getCountry());
    }

    @Test
    void setNationality_should_set_nationality() {
        Nationality value = Nationality.AUSTRALIAN;
        request.setNationality(value);
        Assertions.assertEquals(value, request.getNationality());
    }

    @Test
    void setBirthday_should_set_birthday() {
        Instant value = Instant.EPOCH;
        request.setBirthDay(value);
        Assertions.assertEquals(value, request.getBirthDay());
    }

    @Test
    void setGender_should_set_gender() {
        Gender value = Gender.MALE;
        request.setGender(value);
        Assertions.assertEquals(value, request.getGender());
    }

    @Test
    void setNationalId_should_set_nationalId() {
        String value = "nationalId";
        request.setNationalId(value);
        Assertions.assertEquals(value, request.getNationalId());
    }
}
