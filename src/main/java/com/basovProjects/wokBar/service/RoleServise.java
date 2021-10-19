package com.basovProjects.wokBar.service;

import java.util.List;

public interface RoleServise<I,E> {
    E findById(I id);
    E findByName(String name);
    List<E> findAll();
}
