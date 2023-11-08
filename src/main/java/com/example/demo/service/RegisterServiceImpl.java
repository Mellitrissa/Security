package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class RegisterServiceImpl implements RegisterService{
    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(UserDTO userDTO) {
        User newUser = User.builder()
                .name (userDTO.getName())
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        newUser.setRoles(Collections.singleton(new Role(7L, "ROLE_USER")));
        userRepository.save(newUser);
    }
}

