package com.basovProjects.wokBar.service;

import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;
import com.basovProjects.wokBar.model.User;

import java.util.List;

public interface UserService<I,E> {
    boolean save(E e);
    void update(E e);
    boolean delete(I id);
    E findUserById(I id);
    E findUserByUserName(String name) throws MyObjectNotFoundException;
    List<E> allUsers();
}