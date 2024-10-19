package com.globalsoftwaresupport.spring.services.interfaces;

import com.globalsoftwaresupport.spring.dto.request.UserRegistrationRequest;
import com.globalsoftwaresupport.spring.dto.response.UsersResponse;
import com.globalsoftwaresupport.spring.repositories.entity.User;

import java.util.List;

public interface IRegistrationService {

    User findUserByUsername(String username);

    void registerUser(UserRegistrationRequest registrationRequest);

    List<UsersResponse> findAllUsers();
}
