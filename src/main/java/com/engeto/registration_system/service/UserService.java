package com.engeto.registration_system.service;

import com.engeto.registration_system.dto.UserRequestDto;
import com.engeto.registration_system.dto.UserResponseDto;

public interface UserService {

    UserRequestDto createUser(UserResponseDto request);
}
