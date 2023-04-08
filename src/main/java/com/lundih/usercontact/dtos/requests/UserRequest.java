package com.lundih.usercontact.dtos.requests;

import com.lundih.usercontact.enums.Country;
import com.lundih.usercontact.enums.Gender;
import com.lundih.usercontact.enums.Nationality;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
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
    @NotNull
    private String firstName;

    @Getter
    @Setter
    @NotNull
    private String lastName;

    @Getter
    @Setter
    @NotNull
    private Country country;

    @Getter
    @Setter
    @NotNull
    private Nationality nationality;

    @Getter
    @Setter
    @NotNull
    private Instant birthDay;

    @Getter
    @Setter
    @NotNull
    private Gender gender;

    @Getter
    @Setter
    @NotNull
    private String nationalId;
}
