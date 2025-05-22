package com.engeto.registration_system.exception;

/**
 * Generic runtime exception used for validation-related failures,
 * especially when failing to load or process static resources.
 */
public class ValidationRuntimeException extends RuntimeException {
    public ValidationRuntimeException(String message) {
        super(message);
    }
}
