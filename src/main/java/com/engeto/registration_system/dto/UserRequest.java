package com.engeto.registration_system.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @NotBlank(groups = {ValidationGroups.Update.class, ValidationGroups.Create.class})
    @Size(min = 2, max = 50,
            groups = {ValidationGroups.Update.class, ValidationGroups.Create.class},
            message = "Name must be between 2 and 30 characters long.")
    @JsonView({Views.Public.class, Views.Update.class})
    private String name;

    @NotBlank(groups = {ValidationGroups.Update.class, ValidationGroups.Create.class})
    @Size(min = 2, max = 50,
            groups = {ValidationGroups.Update.class, ValidationGroups.Create.class},
            message = "Surname must be between 2 and 50 characters long.")
    @JsonView({Views.Public.class, Views.Update.class})
    private String surname;

    @NotBlank(groups = ValidationGroups.Create.class)
    @Size(min = 12, max = 12,
            groups = ValidationGroups.Create.class,
            message = "personID must be exactly 12 characters")
    @JsonView(Views.Public.class)
    private String personID;
}
