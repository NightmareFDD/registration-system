package com.engeto.registration_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    @JsonView({Views.Public.class})
    private Long id;

    @JsonView({Views.Public.class, Views.Update.class})
    private String name;

    @JsonView({Views.Public.class, Views.Update.class})
    private String surname;

    @JsonView({Views.Public.class})
    private String personID;

    @JsonView({Views.Public.class})
    private String uuid;
}
