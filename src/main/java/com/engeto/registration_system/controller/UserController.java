package com.engeto.registration_system.controller;

import com.engeto.registration_system.dto.UserRequest;
import com.engeto.registration_system.dto.UserResponse;
import com.engeto.registration_system.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody @Valid UserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id, @RequestParam(defaultValue = "false") Boolean detail) {
        return userService.getUser(id, Boolean.TRUE.equals(detail));
    }

    @GetMapping
    public List<UserResponse> getAllUsers(@RequestParam(defaultValue = "false") Boolean detail) {
        return userService.getAllUsers(Boolean.TRUE.equals(detail));
    }
}
