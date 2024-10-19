package com.globalsoftwaresupport.spring.services;

import com.globalsoftwaresupport.spring.dto.request.UserRegistrationRequest;
import com.globalsoftwaresupport.spring.dto.response.UsersResponse;
import com.globalsoftwaresupport.spring.enums.Roles;
import com.globalsoftwaresupport.spring.repositories.dao.UserRepository;
import com.globalsoftwaresupport.spring.repositories.entity.Role;
import com.globalsoftwaresupport.spring.repositories.entity.User;
import com.globalsoftwaresupport.spring.services.interfaces.IRegistrationService;
import com.globalsoftwaresupport.spring.services.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements IRegistrationService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final IRoleService roleService;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository, IRoleService roleService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.roleService = roleService;
    }

    @Override
    public User findUserByUsername(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            return userRepository.findByUsername(username).get();
        }
        return null;
    }

    @Override
    public void registerUser(UserRegistrationRequest userRegistrationRequest) {

        User user = new User();
        user.setUsername(userRegistrationRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userRegistrationRequest.getPassword()));
        user.setRoles(Collections.singleton(roleService.getRole(Roles.ROLE_USER.getRoleName())));
        userRepository.save(user);
    }

    @Override
    public List<UsersResponse> findAllUsers() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(user -> new UsersResponse(user.getUsername(),
                        user.getRoles().stream()
                                .map(Role::getName)
                                .collect(Collectors.joining(","))))
                .collect(Collectors.toList());
    }
}
