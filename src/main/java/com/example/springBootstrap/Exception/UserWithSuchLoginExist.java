package com.example.springBootstrap.Exception;

import org.springframework.dao.DataIntegrityViolationException;

public class UserWithSuchLoginExist extends DataIntegrityViolationException {
    public UserWithSuchLoginExist(String msg) {
        super(msg);
    }
}
