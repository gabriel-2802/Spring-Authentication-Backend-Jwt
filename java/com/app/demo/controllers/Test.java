package com.app.demo.controllers;

import com.app.demo.entities.User;
import com.app.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class Test {

    private final UserRepository userRepository;
    @GetMapping("/all")
    public List<User> all() {
        return userRepository.findAll();
    }

    @GetMapping("/user")
    public String user() {
        return "User Content.";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin Content.";
    }

}
