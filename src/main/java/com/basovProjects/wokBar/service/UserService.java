package com.basovProjects.wokBar.service;

import java.util.List;

public interface UserService<I,E> {
    boolean save(E e);
    void update(E e);
    boolean delete(I id);
    E findUserById(I id);
    List<E> allUsers();
}
