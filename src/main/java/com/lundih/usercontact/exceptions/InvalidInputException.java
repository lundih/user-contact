package com.lundih.usercontact.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception intended to be thrown when a user provides invalid input
 *
 * @author lundih
 * @since 0.0.1
 */
public class InvalidInputException extends ResponseStatusException {
    public InvalidInputException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
