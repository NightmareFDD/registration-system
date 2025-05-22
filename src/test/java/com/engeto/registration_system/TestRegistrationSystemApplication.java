package com.engeto.registration_system;

import org.springframework.boot.SpringApplication;

public class TestRegistrationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.from(RegistrationSystemApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
