package com.lundih.usercontact.dtos.requests;

import com.lundih.usercontact.enums.Country;
import com.lundih.usercontact.enums.Gender;
import com.lundih.usercontact.enums.Nationality;
import lombok.Getter;
import lombok.NonNull;
import java.time.Instant;

/**
 * Request body for {@link com.lundih.usercontact.entities.User}
 *
 * This request does not allow null values to be provided ro ensure uniformity of user data across users
 *
 * @author lundih
 * @since 0.0.1
 */
public class UserRequest {
    @Getter
    private final String firstName;

    @Getter
    private final String lastName;

    @Getter
    private final Country country;

    @Getter
    private final Nationality nationality;

    @Getter
    private final Instant birthDay;

    @Getter
    private final Gender gender;

    @Getter
    private final String nationalId;

    public UserRequest(@NonNull String firstName, @NonNull String lastName, @NonNull Country country,
                       @NonNull Nationality nationality, @NonNull Instant birthDay, @NonNull Gender gender,
                       @NonNull String nationalId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.nationality = nationality;
        this.birthDay = birthDay;
        this.gender = gender;
        this.nationalId = nationalId;
    }
}
