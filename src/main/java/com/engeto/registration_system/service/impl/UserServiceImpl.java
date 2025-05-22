package com.engeto.registration_system.service.impl;

import com.engeto.registration_system.dto.UserResponse;
import com.engeto.registration_system.dto.UserRequest;
import com.engeto.registration_system.mapper.UserMapper;
import com.engeto.registration_system.model.User;
import com.engeto.registration_system.repository.UserRepository;
import com.engeto.registration_system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toEntity(request);
        User saved = userRepository.save(user);
        log.info("User created with ID: {} and UUID: {}", user.getId(), user.getUuid());

        return userMapper.toDto(saved);
    }
}
