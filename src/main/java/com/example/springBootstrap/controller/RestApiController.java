package com.example.springBootstrap.controller;

import com.example.springBootstrap.Exception.Info;
import com.example.springBootstrap.Exception.UserWithSuchLoginExist;
import com.example.springBootstrap.model.User;
import com.example.springBootstrap.service.RoleService;
import com.example.springBootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    private final UserService userService;

    @Autowired
    public RestApiController(RoleService roleService, UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<Info> createUser(@RequestBody User user) {
        try {
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (UserWithSuchLoginExist u) {
            throw new UserWithSuchLoginExist("User with username exist");
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Info> pageDelete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(new Info("User deleted"), HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUser (@PathVariable("id") long id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername (Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Info> pageEdit(@PathVariable("id") long id, @RequestBody User user) {
        try {
            userService.update(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (UserWithSuchLoginExist u) {
            throw new UserWithSuchLoginExist("User with username exist");
        }
    }

}