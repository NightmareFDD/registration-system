package com.engeto.registration_system.service.impl;

import com.engeto.registration_system.dto.UserRequestDto;
import com.engeto.registration_system.dto.UserResponseDto;
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


    @Override
    public UserRequestDto createUser(UserResponseDto request) {
        return null;
    }
}
