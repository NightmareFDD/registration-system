package com.engeto.registration_system.service;

import com.engeto.registration_system.dto.UserRequest;
import com.engeto.registration_system.dto.UserResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    UserResponse getUser(Long id, boolean detail);

    List<UserResponse> getAllUsers(@RequestParam(defaultValue = "false") boolean detail);
}
