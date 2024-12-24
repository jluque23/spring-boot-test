package com.globalsoftwaresupport.spring.utils;

import com.globalsoftwaresupport.spring.dto.request.UserRegistrationRequest;
import com.globalsoftwaresupport.spring.repositories.entity.User;

import java.util.Date;

public class UserGeneration {

    public User generateUser(UserRegistrationRequest registrationRequest) {
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setPhoneNumber(registrationRequest.getPhoneNumber());
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.setLastLogin(new Date());
        user.setActive(true);
        user.setDeleted(false);

        return user;
    }
}
