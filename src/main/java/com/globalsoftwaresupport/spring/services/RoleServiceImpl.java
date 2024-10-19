package com.globalsoftwaresupport.spring.services;

import com.globalsoftwaresupport.spring.enums.Roles;
import com.globalsoftwaresupport.spring.repositories.dao.RoleRepository;
import com.globalsoftwaresupport.spring.repositories.entity.Role;
import com.globalsoftwaresupport.spring.services.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRole(String roleName) {
        return roleRepository.findByName(Roles.ROLE_USER.getRoleName()).orElseThrow(() -> new RuntimeException("Role not found"));
    }
}
