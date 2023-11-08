package com.example.demo.repositories;

import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
    List<Role> findRolesByNameIn(List<String> roleNames);
    Role findByUserId(Long userId);
}
