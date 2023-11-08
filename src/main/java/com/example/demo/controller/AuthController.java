package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.service.RegisterService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;

import javax.validation.Valid;

@Controller
public class AuthController {
    private final UserService service;
    private final RegisterService registerService;

    public AuthController(UserService service, RegisterService registerService) {
        this.service = service;
        this.registerService = registerService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String showRegistrationPage(Authentication authentication,Model model){
        if (authentication != null) {
            return "redirect:/";
        }
        model.addAttribute("userDTO", new User());
        return "register";
    }
    @PostMapping("/register")
    public String signUpUser(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "register";
        }
        registerService.addUser(userDTO);
        return "redirect:/login";
    }



}