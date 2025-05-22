package com.engeto.registration_system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String details;

    public ApiErrorResponse(int status, String error, String details) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.details = details;
    }
}
