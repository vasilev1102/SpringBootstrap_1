package com.example.springBootstrap.Exception;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Info {

    private String info;

    public Info() {
    }

    public Info(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
