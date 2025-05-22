package com.engeto.registration_system.controller;

import com.engeto.registration_system.dto.UserRequestDto;
import com.engeto.registration_system.dto.UserResponseDto;
import com.engeto.registration_system.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserRequestDto createUser(@RequestBody @Valid UserResponseDto request) {
        return userService.createUser(request);
    }
}
