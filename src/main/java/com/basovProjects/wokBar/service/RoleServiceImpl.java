package com.basovProjects.wokBar.service;

import com.basovProjects.wokBar.model.Role;
import com.basovProjects.wokBar.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService<Long, Role> {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role findById(Long id) {
        return roleRepository.getById(id);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}




