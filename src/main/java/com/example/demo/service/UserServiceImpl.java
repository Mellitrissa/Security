package com.example.demo.service;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

@Autowired
    private  final UserRepository userRepository;
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
    public Optional <User> findUserById(Long id) {
        return  findUserById(id);
    }

    @Override
    @Transactional
    public Optional<User> saveUser(User user){
        if (user==null){
            throw new RuntimeException("User is null");
        }
        return Optional.of(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Looking for user by username: " + username + " in UserServiceImpl");
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        logger.info("User found in UserServiceImpl. User: " + user);
        return user;
    }
}
