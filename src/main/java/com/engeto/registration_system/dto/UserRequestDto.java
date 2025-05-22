package com.engeto.registration_system.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    private Long id;
    private String name;
    private String surname;
}
