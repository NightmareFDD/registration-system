package com.engeto.registration_system.mapper;

import com.engeto.registration_system.dto.UserRequest;
import com.engeto.registration_system.dto.UserResponse;
import com.engeto.registration_system.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Mapper class responsible for converting between {@link User} entities
 * and DTOs: {@link UserRequest} and {@link UserResponse}.
 *
 * <p>This class is used by the service layer to map incoming requests to entities
 * and convert entities to response objects returned by the API.</p>
 */

@Slf4j
@Component
public class UserMapper {

    /**
     * Converts a {@link UserRequest} DTO into a {@link User} entity.
     * UUID is generated automatically in this method.
     *
     * @param request the DTO with user input data
     * @return new {@link User} entity with generated UUID
     */
    public User toEntity(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setPersonID(request.getPersonID());
        user.setUuid(UUID.randomUUID().toString());
        return user;
    }

    /**
     * Converts a {@link User} entity to a basic {@link UserResponse} DTO.
     * This version includes only ID, name, and surname.
     *
     * @param user the {@link User} entity to convert
     * @return a simplified {@link UserResponse} DTO
     */
    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }

    /**
     * Converts a {@link User} entity to a detailed {@link UserResponse} DTO.
     * This version includes ID, name, surname, personID, and UUID.
     *
     * @param user the {@link User} entity to convert
     * @return a detailed {@link UserResponse} DTO with all public fields
     */
    public UserResponse toDetailResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .personID(user.getPersonID())
                .uuid(user.getUuid())
                .build();
    }

}