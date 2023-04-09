package com.lundih.usercontact.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception intended to be thrown for Conflicting entries
 *
 * @author lundih
 * @since 0.0.1
 */
public class DuplicateEntryException extends ResponseStatusException {
    public DuplicateEntryException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
