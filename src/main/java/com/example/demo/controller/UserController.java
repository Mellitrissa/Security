package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/user")
@Controller
public class UserController {
    private final UserService service;
    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository) {
        this.service = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping()
    public String getUserPage(Principal principal, Model model) {
        User user= (User) service.loadUserByUsername(principal.getName());
        Role role = roleRepository.findByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("role", role.getName());
        return "user";
    }
}
