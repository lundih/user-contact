package com.lundih.usercontact.dtos.requests;

import com.lundih.usercontact.enums.Country;
import com.lundih.usercontact.enums.Gender;
import com.lundih.usercontact.enums.Nationality;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Request body for editing {@link com.lundih.usercontact.entities.User}
 * <P>
 * This request allows null values to be provided in order to allow users to only change values that need to be
 * changed without having to resubmit the whole user object
 *
 * @author lundih
 * @since 0.0.1
 */
public class UserEditRequest {
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
