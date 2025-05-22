package com.engeto.registration_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Size(min = 12, max = 12)
    private String personID;
}
