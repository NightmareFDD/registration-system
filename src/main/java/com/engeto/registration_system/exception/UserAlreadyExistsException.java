package com.engeto.registration_system.exception;

/**
 * Exception thrown when attempting to create a user that already exists
 * based on a unique constraint (e.g. personID).
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
