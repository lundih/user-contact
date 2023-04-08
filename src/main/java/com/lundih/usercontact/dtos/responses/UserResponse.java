package com.lundih.usercontact.dtos.responses;


import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Response for {@link com.lundih.usercontact.entities.User}
 *
 * @author lundih
 * @since 0.0.1
 */
public class UserResponse {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String country;

    @Getter
    @Setter
    private String nationality;

    @Getter
    @Setter
    private Instant birthDay;

    @Getter
    @Setter
    private String gender;

    @Getter
    @Setter
    private String nationalId;

    @Getter
    @Setter
    private String createdOn;

    @Getter
    @Setter
    private String updatedOn;
}
