package com.basovProjects.wokBar.service;

import java.util.List;

public interface UserService<I,E> {
    void save(E e);
    void update(E e);
    void delete(I id);
    E findUserById(I id);
    E findUserByUsername(String name);
    List<E> findAll();
}
