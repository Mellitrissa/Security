package com.example.demo.service;


import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    List<User> findAll ();
    Optional<User> saveUser(User user);
    void deleteUserById(Long id);
    Optional<User> findUserByUsername (String username);

    Optional <User> findUserById(Long id);
}
