package com.example.springBootstrap.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.springBootstrap.model.Role;
import com.example.springBootstrap.model.User;
import com.example.springBootstrap.service.RoleService;
import com.example.springBootstrap.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DefoultInit {

    private final UserService userService;

    private final RoleService roleService;
    @Autowired
    public DefoultInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void dataBaseInit() {
        roleService.addRole(new Role(1L,"ROLE_USER"));
        roleService.addRole(new Role(2L,"ROLE_ADMIN"));
        Set<Role> role= new HashSet<>();
        role.add(roleService.getRoleById(1L));
        role.add(roleService.getRoleById(2L));
        User user = new User("admin", "adminov", (byte) 20, "admin@mail", "admin", "admin", role);
        userService.save(user);
    }

}
