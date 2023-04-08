package com.lundih.usercontact.dtos.requests;

import com.lundih.usercontact.enums.Country;
import com.lundih.usercontact.enums.Gender;
import com.lundih.usercontact.enums.Nationality;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Request body for {@link com.lundih.usercontact.entities.User}
 *
 * @author lundih
 * @since 0.0.1
 */
public class UserRequest {
    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private Country country;

    @Getter
    @Setter
    private Nationality nationality;

    @Getter
    @Setter
    private Instant birthDay;

    @Getter
    @Setter
    private Gender gender;

    @Getter
    @Setter
    private String nationalId;
}
