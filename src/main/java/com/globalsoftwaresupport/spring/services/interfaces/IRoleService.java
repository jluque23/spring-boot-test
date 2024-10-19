package com.globalsoftwaresupport.spring.services.interfaces;

import com.globalsoftwaresupport.spring.repositories.entity.Role;

public interface IRoleService {
    Role getRole(String roleName);
}
