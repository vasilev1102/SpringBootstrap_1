package com.example.springBootstrap.service;

import com.example.springBootstrap.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> findAllRole();
    Set<Role> findByIdRoles(List<Long>roles);

    void addRole(Role role);

    Role getRoleById(Long id);
}
