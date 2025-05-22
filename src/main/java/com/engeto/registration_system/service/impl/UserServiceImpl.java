package com.engeto.registration_system.service.impl;

import com.engeto.registration_system.dto.UserRequest;
import com.engeto.registration_system.dto.UserResponse;
import com.engeto.registration_system.mapper.UserMapper;
import com.engeto.registration_system.model.User;
import com.engeto.registration_system.repository.UserRepository;
import com.engeto.registration_system.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserResponse createUser(@NotNull UserRequest request) {
        User user = userMapper.toRequest(request);
        User saved = userRepository.save(user);
        log.info("User created with ID: {} and UUID: {}", user.getId(), user.getUuid());

        return userMapper.toResponse(saved);
    }

    @Override
    public UserResponse getUser(Long id, boolean detail) {
        // TODO: create custom exception
        User user = findUserById(id);

        return detail ? userMapper.toDetailResponse(user) : userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers(boolean detail) {
        return userRepository.findAll().stream()
                .map(user -> detail ? userMapper.toDetailResponse(user) : userMapper.toResponse(user))
                .toList();
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = findUserById(id);
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        User updated = userRepository.save(user);
        log.info("User with ID {} updated successfully", id);

        return userMapper.toResponse(updated);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        log.info("User with ID {} deleted successfully", id);
    }


    // === Helper methods ===

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found."));
    }

}
