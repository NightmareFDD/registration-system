package com.engeto.registration_system.service;

import com.engeto.registration_system.dto.UserRequest;
import com.engeto.registration_system.dto.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest request);
}
