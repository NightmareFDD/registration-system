package com.engeto.registration_system.exception;

/**
 * Exception thrown when the provided personID is not in the allowed list.
 */
public class PersonIdNotAllowedException extends RuntimeException {
    public PersonIdNotAllowedException(String message) {
        super(message);
    }
}
