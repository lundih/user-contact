package com.lundih.usercontact.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception thrown when one attempts to retrieve a user with an ID  that does not exist
 *
 * @author lundih
 * @since 0.0.1
 */
public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, String.format("User with id %s was not found", id));
    }
}