package com.basovProjects.wokBar.service.impl.security;

import com.basovProjects.wokBar.model.Role;
import com.basovProjects.wokBar.model.User;
import com.basovProjects.wokBar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {

        username = username.toLowerCase();
        User user;
        try {
            user = userRepository.findByUsername(username);
        } catch (ObjectRetrievalFailureException orfe) {
            throw new UsernameNotFoundException("User '" + username
                    + "' could not be found.");
        }

        Set<Role> roles = user.getRoles();

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(username,
                user.getPassword(), user.isEnabled(), true, true, true,
                authorities);

    }
}
