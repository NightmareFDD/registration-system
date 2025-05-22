package com.engeto.registration_system.repository;

import com.engeto.registration_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Object> findByPersonID(String personID);
}
