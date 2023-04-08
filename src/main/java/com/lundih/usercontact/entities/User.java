package com.lundih.usercontact.entities;

import com.lundih.usercontact.enums.Country;
import com.lundih.usercontact.enums.Gender;
import com.lundih.usercontact.enums.Nationality;
import com.lundih.usercontact.exceptions.InvalidInputException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

/**
 * Class that represents a user in the system whose details are to be captured
 *
 * @author lundih
 * @since 0.0.1
 */
@Entity
@Table(name = "users")
public class User {
    /**
     * Unique auto-generated ID
     */
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    /**
     * User's first name
     */
    @Getter
    @Setter
    @Column(name = "first_name")
    private String firstName;

    /**
     * User's last name
     */
    @Getter
    @Setter
    @Column(name = "last_name")
    private String lastName;

    /**
     * User's country of residence. A {@link com.lundih.usercontact.enums.Country} enum is used
     */
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Country country;

    /**
     * User's nationality. A {@link com.lundih.usercontact.enums.Nationality} enum is used
     */
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Nationality nationality;

    /**
     * User's date of birth
     */
    @Getter
    @Setter
    private Instant birthDay;

    /**
     * User's gender. A {@link com.lundih.usercontact.enums.Gender} enum is used
     */
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * Number on the user's national identification document
     */
    @Getter
    @Setter
    private String nationalId;

    /**
     * Auto-generated creation timestamp
     */
    @Getter
    @Setter
    @CreationTimestamp
    @Column(name =  "created_on")
    private Instant createdOn;

    /**
     * Auto-generated update timestamp
     */
    @Getter
    @Setter
    @UpdateTimestamp
    @Column(name = "updated_on")
    private Instant updatedOn;

    /**
     * Checks for invalid fields in an object and throws a corresponding exception if an invalid field is found
     */
    public void validate() throws InvalidInputException {
        if (birthDay != null) {
            if (birthDay.isAfter(Instant.now()))
                throw new InvalidInputException("The date of birth should not be in the future");
        }
    }
}
