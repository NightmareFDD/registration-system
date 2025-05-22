package com.engeto.registration_system.repository;

import com.engeto.registration_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{
}
