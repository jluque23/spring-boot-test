package com.globalsoftwaresupport.spring.controllers;

import com.globalsoftwaresupport.spring.dto.request.UserRegistrationRequest;
import com.globalsoftwaresupport.spring.dto.response.UsersResponse;
import com.globalsoftwaresupport.spring.services.interfaces.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    private final IRegistrationService registrationService;

    @Autowired
    public RegistrationController(IRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {

        ResponseEntity<String> Passwords_do_not_match = passwordValidationsRegistrationRequest(registrationRequest);
        if (Passwords_do_not_match != null) return Passwords_do_not_match;

        registrationService.registerUser(registrationRequest);

        return ResponseEntity.ok("User registered successfully User: " + registrationRequest.getUsername());
    }

    @GetMapping("/getall")
    public ResponseEntity<List<UsersResponse>> getAllUsers() {
        return ResponseEntity.ok(registrationService.findAllUsers());
    }

    private ResponseEntity<String> passwordValidationsRegistrationRequest(UserRegistrationRequest registrationRequest) {
        if (!registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }

        if (registrationService.findUserByUsername(registrationRequest.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already in use");
        }
        return null;
    }
}
