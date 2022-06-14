package com.example.springBootstrap.service;

import com.example.springBootstrap.model.User;
import com.example.springBootstrap.repositories.RoleRepository;
import com.example.springBootstrap.repositories.UserRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepositories userRepositories;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepositories userRepositories, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepositories = userRepositories;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User passwordCoder(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepositories.findAll();
    }

    @Override
    public User getById(long id) {
        User user = null;
        Optional<User> optional = userRepositories.findById(id);
        if(optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @Override
    public void save(User user) {
        userRepositories.save(passwordCoder(user));
    }

    @Override
    public void update(User user) {
        userRepositories.save(user);
    }

    @Override
    public void deleteById(long id) {
        userRepositories.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepositories.findByUsername(username);
    }


}

