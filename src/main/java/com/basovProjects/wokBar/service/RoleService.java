package com.basovProjects.wokBar.service;

import java.util.List;

public interface RoleService<I,E> {
    E findById(I id);
    E findByName(String name);
    List<E> findAll();
}
