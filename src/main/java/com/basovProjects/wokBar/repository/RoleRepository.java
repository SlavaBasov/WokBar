package com.basovProjects.wokBar.repository;

import com.basovProjects.wokBar.model.Role;
import com.basovProjects.wokBar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
