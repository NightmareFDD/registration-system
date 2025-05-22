package com.engeto.registration_system.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleUserExists(UserAlreadyExistsException ex) {
        log.warn("UserAlreadyExistsException: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.CONFLICT, "User already exists", ex.getMessage());
    }

    @ExceptionHandler(PersonIdNotAllowedException.class)
    public ResponseEntity<ApiErrorResponse> handlePersonIdNotAllowed(PersonIdNotAllowedException ex) {
        log.warn("PersonIdNotAllowedException: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid personID", ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        log.warn("UserNotFoundException: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, "User not found", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String validationDetails = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        log.warn("Validation error(s): {}", validationDetails);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Validation failed", validationDetails);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(Exception ex) {
        log.error("Unhandled exception: ", ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred", ex.getMessage());
    }

    // === Helper methods ===

    private ResponseEntity<ApiErrorResponse> buildErrorResponse(HttpStatus status, String error, String details) {
        return ResponseEntity.status(status)
                .body(new ApiErrorResponse(status.value(), error, details));
    }
}
