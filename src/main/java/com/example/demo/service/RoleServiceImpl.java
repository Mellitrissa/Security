package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.repositories.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Optional<Role> findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> findRolesByNameIn(List<String> roleNames) {
        return roleRepository.findRolesByNameIn(roleNames);
    }

}
