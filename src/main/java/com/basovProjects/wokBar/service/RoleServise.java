package com.basovProjects.wokBar.service;

import java.util.List;

public interface RoleServise<I,E> {
    E getById(I id);
    List<E> getAll();
}
