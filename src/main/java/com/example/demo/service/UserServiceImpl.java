package com.example.demo.service;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private  final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
        logger.info("UserServiceImpl constructor" + userRepository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findAllById(Long id) {
        return  userRepository.findAllById(id);
    }

    @Override
    @Transactional
    public void updateUser(Long userId, UserDTO updateData) {
        User userForUpdate = userRepository.findById(userId).orElseThrow();
        userForUpdate.setName(updateData.getName());
        userForUpdate.setUsername(updateData.getUsername());
        userRepository.save(userForUpdate);
    }
    @Override
    @Transactional
   public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Looking for user by username: " + username + " in UserServiceImpl");
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        logger.info("User found in UserServiceImpl. User: " + user);
        return  user;
    }
}
