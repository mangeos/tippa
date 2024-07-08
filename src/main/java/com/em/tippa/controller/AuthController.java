package com.em.tippa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.em.tippa.dto.RegistrationDto;
import com.em.tippa.services.UserService;

@Controller
public class AuthController {
    private UserService userService;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin() {
        System.out.println("inne!!!");

        return "redirect:/home";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String postRegister() {
        System.out.println("innes!!!");
        return "redirect:/home";
    }
}
