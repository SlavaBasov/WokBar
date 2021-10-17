package com.basovProjects.wokBar.service;

import com.basovProjects.wokBar.model.User;
import com.basovProjects.wokBar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService<Long, User>{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public void update(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findUserByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
