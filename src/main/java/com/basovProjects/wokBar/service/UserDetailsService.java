package com.basovProjects.wokBar.service;

import com.basovProjects.wokBar.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
    public UserDetails loadUserByUsername(String username);
}
