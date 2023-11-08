package com.example.demo.controller;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.service.RegisterService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    private  final UserService userService;
    private final RoleRepository roleRepository;
    @Autowired
    private final RegisterService registerService;
    public AdminController(UserService userService, RoleRepository roleRepository, RegisterService registerService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.registerService = registerService;

    }

    @GetMapping("")
    public String getUsersPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @PostMapping("")
    public String addUser(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "admin";
        }
        registerService.addUser(userDTO);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String getUserPage(@PathVariable("id") Long id, Model model) {
        User user = userService.findAllById(id);
        Role role = roleRepository.findByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("role", role.getName());
        return "/userId";
    }
    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Long userId, UserDTO user) {
        userService.updateUser(userId,user);
        return "redirect:/admin/" +userId;
    }

    @GetMapping("/{id}/delete")
    public String updateUser(@PathVariable("id") Long userId) {
        userService.deleteUserById(userId);
        return "redirect:/admin";
    }

}
