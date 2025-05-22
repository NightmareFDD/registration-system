package com.engeto.registration_system.dto;

/**
 * Marker interfaces pro Jackson @JsonView.
 */
public interface Views {
    /**
     * Basic view – f.ex. GET without details, CREATE etc.
     */
    interface Public {}

    /**
     * View for update – allowing update only name and surname.
     */
    interface Update {}
}