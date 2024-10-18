package com.globalsoftwaresupport.spring.controllers;

import com.globalsoftwaresupport.spring.dto.UserRegistrationRequest;
import com.globalsoftwaresupport.spring.repositories.dao.RoleRepository;
import com.globalsoftwaresupport.spring.repositories.dao.UserRepository;
import com.globalsoftwaresupport.spring.repositories.entity.Role;
import com.globalsoftwaresupport.spring.repositories.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {

        if (registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }

        if (userRepository.findByUsername(registrationRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already in use");
        }

        //CREATE NEW USER AND SET ROLES this is going to go on a service layer.


        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));

        //Assign default role

        Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRoles(Collections.singleton(userRole));

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");

    }
}
