package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    List<User> findAll ();
    void updateUser(Long userId, UserDTO user);

    void deleteUserById(Long id);
    Optional<User> findByUsername (String username);

    User findAllById(Long id);


}
