package com.engeto.registration_system.mapper;

import com.engeto.registration_system.dto.UserRequest;
import com.engeto.registration_system.dto.UserResponse;
import com.engeto.registration_system.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Component responsible for mapping between User entities and DTO objects.
 */

@Slf4j
@Component
public class UserMapper {

    /**
     * Maps a {@link UserResponse} to a {@link User} entity.
     *
     * @param request the user creation DTO
     * @return mapped User entity
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
     * Maps a {@link User} entity to a lightweight {@link UserResponse}.
     *
     * @param user the user entity
     * @return basic user DTO
     */
    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }

    /**
     * Maps a {@link User} entity to a detailed {@link UserResponse}.
     *
     * @param user the user entity
     * @return detailed user DTO
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