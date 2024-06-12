package com.em.tippa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.em.tippa.services.UserService;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController() {
        // this.userService = userService;
    }

    @GetMapping("/")
    public String listUser() {
        return "user-list";
    }
}
