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
//        if (log.isDebugEnabled()) {
//            log.debug("Security verification for user '" + login + "'");
//        }
        User user;
        try {
            user = userRepository.findByUsername(username);
        } catch (ObjectRetrievalFailureException orfe) {
            throw new UsernameNotFoundException("User '" + username
                    + "' could not be found.");
        }
//        user.setLastAccessDate(Calendar.getInstance().getTime());

        Set<Role> roles = user.getRoles();

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

//        return new org.springframework.security.core.userdetails.User(username,
//                user.getPassword(), user.isEnabled(), true, true, true,
//                authorities);

        return new org.springframework.security.core.userdetails.User(username,
                user.getPassword(), user.isEnabled(), true, true, true,
                authorities);

    }

//    private List<GrantedAuthority> buildUserAuthority(String role) {
//        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
//        setAuths.add(new SimpleGrantedAuthority(role));
//        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(
//                setAuths);
//        return result;
//    }
//
//    private User buildUserForAuthentication(User user,
//                                            List<GrantedAuthority> authorities) {
//        User newUser = new User(user.getUsername(), user.getPassword(),
//                user.getPhoneNumber());
//        Set<Role> roles = new HashSet<>();
//        Collections.addAll(roles, (Role) authorities);
//        user.setRoles(roles);
//        return newUser;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        List<GrantedAuthority> authorities = buildUserAuthority("Role");
//        return buildUserForAuthentication(user, authorities);
//    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        return user;
//    }
}
